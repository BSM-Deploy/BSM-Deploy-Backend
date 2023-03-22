package bssm.deploy.domain.container.service;

import bssm.deploy.domain.project.domain.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ContainerBuildService {

    private final LinuxContainerCommandService containerCommandService;

    public void rebuildContainer(Project project) throws IOException {
        containerCommandService.rebuildContainer(project.getId());
    }

}
