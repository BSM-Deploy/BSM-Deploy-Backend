package bssm.deploy.domain.project.service;

import bssm.deploy.domain.project.domain.Project;
import bssm.deploy.domain.project.domain.repository.ProjectRepository;
import bssm.deploy.domain.project.exception.AlreadyExistDomainPrefixException;
import bssm.deploy.domain.project.presentaion.dto.req.CreateProjectReq;
import bssm.deploy.domain.project.presentaion.dto.req.UploadProjectReq;
import bssm.deploy.domain.project.presentaion.dto.res.ProjectRes;
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
import java.io.IOException;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProjectService {

    private final CurrentUser currentUser;
    private final ProjectProvider projectProvider;
    private final ProjectFileValidateService projectFileValidateService;
    private final ProcessProjectFileService processProjectFileService;

    private final ProjectRepository projectRepository;

    @Value("${bsm-deploy.project-path.base}")
    private String PROJECT_BASE_RESOURCE_PATH;
    @Value("${bsm-deploy.project-path.temp}")
    private String PROJECT_TEMP_RESOURCE_PATH;

    public ListRes<ProjectRes> findProjectList() {
        List<ProjectRes> projectResList = projectProvider.findProjectList(currentUser.getUser()).stream()
                .map(ProjectRes::create)
                .toList();
        return ListRes.create(projectResList);
    }

    @Transactional
    public ProjectRes createProject(CreateProjectReq req) {
        if (projectRepository.existsByDomainPrefix(req.getDomainPrefix())) {
            throw new AlreadyExistDomainPrefixException();
        }
        Project project = Project.create(currentUser.getUser(), req.getName(), req.getDomainPrefix(), req.getProjectType());
        project = projectRepository.save(project);
        return ProjectRes.create(project);
    }

    @Transactional
    public void uploadProject(UploadProjectReq req) throws IOException {
        createProjectDir(PROJECT_TEMP_RESOURCE_PATH);
        MultipartFile file = req.getFile();
        Project project = projectProvider.findProject(req.getProjectId(), currentUser.getUser());
        projectFileValidateService.validateFile(file, project.getProjectType());

        File projectDir = createProjectDir(PROJECT_BASE_RESOURCE_PATH + "/" + project.getId());
        File tempProjectFile = new File(PROJECT_TEMP_RESOURCE_PATH + "/" + SecurityUtil.getRandomStr(16));
        file.transferTo(tempProjectFile);

        File projectFile = processProjectFileService.processProjectFile(tempProjectFile, projectDir, project.getProjectType());
        project.setDataSize(FileUtils.sizeOfDirectory(projectFile));
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
}
