package bssm.deploy.domain.container.service;

import bssm.deploy.domain.project.domain.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ContainerBuildService {

    private final LinuxContainerCommandService containerCommandService;

    public void rebuildContainer(Project project) throws Exception {
        containerCommandService.rebuildContainer(project.getId());
    }

}
