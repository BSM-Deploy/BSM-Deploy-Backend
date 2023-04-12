package bssm.deploy.domain.deploy.service;

import bssm.deploy.domain.deploy.presentation.dto.req.DeployProjectReq;
import bssm.deploy.domain.project.domain.Project;
import bssm.deploy.domain.project.domain.type.ProjectType;
import bssm.deploy.domain.project.exception.NoSuchProjectFileException;
import bssm.deploy.domain.project.service.ProjectProvider;
import bssm.deploy.global.auth.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Project project = projectProvider.findByIdAndUser(req.getProjectId(), currentUser.getUser());
        checkProjectFile(project);
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
            deployNextJs(project.getId(), project.getDomainPrefix());
        }
        project.setDeploy(true);
    }

    private void checkProjectFile(Project project) {
        if (project.getDataSize() <= 0) {
            throw new NoSuchProjectFileException();
        }
    }

    private void deploySingleHtmlFile(Long projectId, String domainPrefix) throws IOException {
        deployCommandService.deploySingleHtml(projectId, domainPrefix);
    }

    private void deployMultipleFile(Long projectId, String domainPrefix) throws IOException {
        deployCommandService.deployMultipleFile(projectId, domainPrefix);
    }

    private void deployReactJs(Long projectId, String domainPrefix) throws IOException {
        deployCommandService.deployReactJs(projectId, domainPrefix);
    }

    private void deployNextJs(Long projectId, String domainPrefix) throws IOException {
        deployCommandService.deployNextJsAsync(projectId, domainPrefix);
    }

}
