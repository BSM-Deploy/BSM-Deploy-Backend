package bssm.deploy.domain.container.service;

import bssm.deploy.domain.container.exception.NoSuchContainerException;
import bssm.deploy.domain.project.domain.Project;
import bssm.deploy.domain.project.domain.type.ProjectType;
import bssm.deploy.domain.project.service.ProjectProvider;
import bssm.deploy.domain.user.domain.User;
import bssm.deploy.global.auth.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ContainerLogService {

    private final CurrentUser currentUser;
    private final ProjectProvider projectProvider;
    private final LinuxContainerLogCommandService containerLogCommandService;

    public String getContainerLog(Long projectId) throws IOException {
        User user = currentUser.getCachedUser();
        Project project = projectProvider.findByIdAndUser(projectId, user);
        validateContainerProject(project);
        return containerLogCommandService.getContainerLog(projectId);
    }

    private void validateContainerProject(Project project) {
        if (!project.isDeploy()) {
            throw new NoSuchContainerException();
        }
        if (ProjectType.BUILT_NEXT_JS == project.getProjectType()) {
            return;
        }
        throw new NoSuchContainerException();
    }

}
