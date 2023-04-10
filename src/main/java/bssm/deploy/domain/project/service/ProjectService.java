package bssm.deploy.domain.project.service;

import bssm.deploy.domain.container.service.ContainerBuildService;
import bssm.deploy.domain.container.service.ContainerService;
import bssm.deploy.domain.deploy.presentation.dto.req.CancelDeployProjectReq;
import bssm.deploy.domain.deploy.service.CancelDeployService;
import bssm.deploy.domain.project.domain.Project;
import bssm.deploy.domain.project.domain.repository.ProjectRepository;
import bssm.deploy.domain.project.domain.repository.ReservedDomainPrefixRepository;
import bssm.deploy.domain.project.exception.AlreadyExistDomainPrefixException;
import bssm.deploy.domain.project.presentaion.dto.req.CreateProjectReq;
import bssm.deploy.domain.project.presentaion.dto.req.UploadProjectReq;
import bssm.deploy.domain.project.presentaion.dto.res.ProjectRes;
import bssm.deploy.domain.user.domain.User;
import bssm.deploy.global.auth.CurrentUser;
import bssm.deploy.global.dto.ListRes;
import bssm.deploy.global.error.exceptions.FileUploadException;
import bssm.deploy.global.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProjectService {

    private final CurrentUser currentUser;
    private final ProjectProvider projectProvider;
    private final ProjectFileValidateService projectFileValidateService;
    private final ProjectFileService processProjectFileService;
    private final ContainerService containerService;
    private final ContainerBuildService containerBuildService;
    private final CancelDeployService cancelDeployService;

    private final ProjectRepository projectRepository;
    private final ReservedDomainPrefixRepository reservedDomainPrefixRepository;

    @Value("${bsm-deploy.project-path.base}")
    private String PROJECT_BASE_RESOURCE_PATH;
    @Value("${bsm-deploy.project-path.temp}")
    private String PROJECT_TEMP_RESOURCE_PATH;

    public ProjectRes findProject(Long projectId) {
        User user = currentUser.getCachedUser();
        Project project = projectProvider.findByIdAndUser(projectId, user);
        return ProjectRes.create(project);
    }

    public ListRes<ProjectRes> findProjectList() {
        User user = currentUser.getCachedUser();
        List<ProjectRes> projectResList = projectProvider.findProjectList(user).stream()
                .map(ProjectRes::create)
                .toList();
        return ListRes.create(projectResList);
    }

    @Transactional
    public ProjectRes createProject(CreateProjectReq req) {
        User user = currentUser.getCachedUser();
        if (projectRepository.existsByDomainPrefix(req.getDomainPrefix())
                || reservedDomainPrefixRepository.existsById(req.getDomainPrefix())) {
            throw new AlreadyExistDomainPrefixException();
        }
        Project project = Project.create(user, req.getName(), req.getDomainPrefix(), req.getProjectType());
        project = projectRepository.save(project);
        return ProjectRes.create(project);
    }

    @Transactional
    public void uploadProject(UploadProjectReq req) throws Exception {
        User user = currentUser.getCachedUser();
        createProjectDir(PROJECT_TEMP_RESOURCE_PATH);
        MultipartFile file = req.getFile();
        Project project = projectProvider.findByIdAndUser(req.getProjectId(), user);
        projectFileValidateService.validateFile(file, project.getProjectType());

        File projectDir = createProjectDir(PROJECT_BASE_RESOURCE_PATH + "/" + project.getId());
        File tempProjectFile = new File(PROJECT_TEMP_RESOURCE_PATH + "/" + SecurityUtil.getRandomStr(16));
        file.transferTo(tempProjectFile);

        processProjectFileService.uploadProjectFile(tempProjectFile, projectDir, project.getProjectType());
        project.setDataSize(FileUtils.sizeOfDirectory(projectDir));

        if (project.checkContainerProject()) {
            containerBuildService.rebuildContainerAsync(project);
        }
    }

    private File createProjectDir(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            try {
                dir.mkdirs();
            } catch (Exception e) {
                e.printStackTrace();
                throw new FileUploadException();
            }
        }
        return dir;
    }

    @Transactional
    public void deleteProject(Long projectId) throws Exception {
        User user = currentUser.getCachedUser();
        Project project = projectProvider.findByIdAndUser(projectId, user);

        try {
            cancelDeployService.cancelDeployProject(CancelDeployProjectReq.ofProjectId(projectId));
        } catch (Exception ignored) {}
        try {
            if (project.checkContainerProject()) {
                containerService.removeContainer(project);
            }
        } catch (Exception ignored) {}
        processProjectFileService.deleteProjectFile(project);
        projectRepository.delete(project);
    }
}
