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
        Long projectId = project.getId();
        String domainPrefix = project.getDomainPrefix();

        if (projectType.equals(ProjectType.SINGLE_HTML)) {
            deployCommandService.deploySingleHtml(projectId, domainPrefix);
        }
        if (projectType.equals(ProjectType.MULTIPLE_FILE)) {
            deployCommandService.deployMultipleFile(projectId, domainPrefix);
        }
        if (projectType.equals(ProjectType.BUILT_REACT_JS)) {
            deployCommandService.deployReactJs(projectId, domainPrefix);
        }
        if (projectType.equals(ProjectType.BUILT_NEXT_JS)) {
            deployCommandService.deployNextJs(projectId, domainPrefix);
        }
        if (projectType.equals(ProjectType.BUILT_SPRING_JAR)) {
        deployCommandService.deploySpringJar(projectId, domainPrefix);
        }
        if (projectType.equals(ProjectType.BUILT_NODE_JS)) {
            deployCommandService.deployNodeJs(projectId, domainPrefix);
        }
        project.setDeploy(true);
    }

    private void checkProjectFile(Project project) {
        if (project.getDataSize() <= 0) {
            throw new NoSuchProjectFileException();
        }
    }

}
