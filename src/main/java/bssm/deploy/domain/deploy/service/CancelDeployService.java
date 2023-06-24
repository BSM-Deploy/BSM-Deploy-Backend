package bssm.deploy.domain.deploy.service;

import bssm.deploy.domain.deploy.presentation.dto.req.CancelDeployProjectReq;
import bssm.deploy.domain.project.domain.Project;
import bssm.deploy.domain.project.domain.type.ProjectType;
import bssm.deploy.domain.project.service.ProjectProvider;
import bssm.deploy.global.auth.CurrentUser;
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
        Long projectId = project.getId();
        ProjectType projectType = project.getProjectType();

        if (projectType.equals(ProjectType.SINGLE_HTML)) {
            cancelDeployCommandService.cancelDeploySingleHtml(projectId);
        }
        if (projectType.equals(ProjectType.MULTIPLE_FILE)) {
            cancelDeployCommandService.cancelDeployMultipleFile(projectId);
        }
        if (projectType.equals(ProjectType.BUILT_REACT_JS)) {
            cancelDeployCommandService.cancelDeployMultipleFile(projectId);
        }
        if (projectType.equals(ProjectType.BUILT_NEXT_JS)) {
            cancelDeployCommandService.cancelDeployNextJs(projectId);
        }
        if (projectType.equals(ProjectType.BUILT_SPRING_JAR)) {
            cancelDeployCommandService.cancelDeploySpringJar(projectId);
        }
        if (projectType.equals(ProjectType.BUILT_NODE_JS)) {
            cancelDeployCommandService.cancelDeployNodeJs(projectId);
        }
        project.setDeploy(false);
    }

}
