package bssm.deploy.domain.deploy.service;

import bssm.deploy.domain.deploy.presentation.dto.req.DeployProjectReq;
import bssm.deploy.domain.project.domain.Project;
import bssm.deploy.domain.project.domain.repository.ProjectRepository;
import bssm.deploy.domain.project.domain.type.ProjectType;
import bssm.deploy.domain.project.exception.AlreadyExistDomainPrefixException;
import bssm.deploy.domain.project.presentaion.dto.req.CreateProjectReq;
import bssm.deploy.domain.project.presentaion.dto.req.UploadProjectReq;
import bssm.deploy.domain.project.service.ProcessProjectFileService;
import bssm.deploy.domain.project.service.ProjectFileValidateService;
import bssm.deploy.domain.project.service.ProjectProvider;
import bssm.deploy.global.auth.CurrentUser;
import bssm.deploy.global.error.exceptions.FileUploadException;
import bssm.deploy.global.error.exceptions.InternalServerException;
import bssm.deploy.global.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DeployService {

    private final CurrentUser currentUser;
    private final ProjectProvider projectProvider;
    private final LinuxDeployCommandService deployCommandService;

    @Transactional
    public void deployProject(DeployProjectReq req) throws IOException {
        Project project = projectProvider.findProject(req.getProjectId(), currentUser.getUser());
        ProjectType projectType = project.getProjectType();

        if (projectType.equals(ProjectType.SINGLE_HTML)) {
            deploySingleHtmlFile(project.getId(), project.getDomainPrefix());
        }
        if (projectType.equals(ProjectType.MULTIPLE_FILE)) {
            deployMultipleFile(project.getId(), project.getDomainPrefix());
        }
        if (projectType.equals(ProjectType.BUILT_REACT_JS)) {
            deployReactJs(project.getId(), project.getDomainPrefix());
        }
        if (projectType.equals(ProjectType.BUILT_NEXT_JS)) {
            throw new InternalServerException();
        }
        project.setDeploy(true);
    }

    private void deploySingleHtmlFile(Long projectId, String domainPrefix) throws IOException {
        deployCommandService.deploySingleHtml(projectId, domainPrefix);
    }

    private void deployMultipleFile(Long projectId, String domainPrefix) throws IOException {
        deployCommandService.deployMultipleFile(projectId, domainPrefix);
    }

    private void deployReactJs(Long projectId, String domainPrefix) throws IOException {
        deployCommandService.deployMultipleFile(projectId, domainPrefix);
    }

}
