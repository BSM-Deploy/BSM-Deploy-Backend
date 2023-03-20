package bssm.deploy.domain.deploy.service;

import bssm.deploy.domain.deploy.presentation.dto.req.CancelDeployProjectReq;
import bssm.deploy.domain.project.domain.Project;
import bssm.deploy.domain.project.domain.type.ProjectType;
import bssm.deploy.domain.project.service.ProjectProvider;
import bssm.deploy.global.auth.CurrentUser;
import bssm.deploy.global.error.exceptions.InternalServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CancelDeployService {

    private final CurrentUser currentUser;
    private final ProjectProvider projectProvider;
    private final LinuxCancelDeployCommandService cancelDeployCommandService;

    @Transactional
    public void cancelDeployProject(CancelDeployProjectReq req) throws IOException {
        Project project = projectProvider.findByIdAndUser(req.getProjectId(), currentUser.getUser());
        ProjectType projectType = project.getProjectType();

        if (projectType.equals(ProjectType.SINGLE_HTML)) {
            cancelDeploySingleHtmlFile(project.getId());
        }
        if (projectType.equals(ProjectType.MULTIPLE_FILE)) {
            cancelDeployMultipleFile(project.getId());
        }
        if (projectType.equals(ProjectType.BUILT_REACT_JS)) {
            cancelDeployReactJs(project.getId());
        }
        if (projectType.equals(ProjectType.BUILT_NEXT_JS)) {
            cancelDeployNextJs(project.getId());
        }
        project.setDeploy(false);
    }

    private void cancelDeploySingleHtmlFile(Long projectId) throws IOException {
        cancelDeployCommandService.cancelDeploySingleHtml(projectId);
    }

    private void cancelDeployMultipleFile(Long projectId) throws IOException {
        cancelDeployCommandService.cancelDeployMultipleFile(projectId);
    }

    private void cancelDeployReactJs(Long projectId) throws IOException {
        cancelDeployCommandService.cancelDeployMultipleFile(projectId);
    }

    private void cancelDeployNextJs(Long projectId) throws IOException {
        cancelDeployCommandService.cancelDeployNextJs(projectId);
    }

}
