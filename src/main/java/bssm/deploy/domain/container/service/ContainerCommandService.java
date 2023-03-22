package bssm.deploy.domain.container.service;

import java.io.IOException;

public interface ContainerCommandService {

    String getContainerLog(long projectId) throws IOException;
}
