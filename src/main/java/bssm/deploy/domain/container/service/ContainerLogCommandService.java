package bssm.deploy.domain.container.service;

import java.io.IOException;

public interface ContainerLogCommandService {

    String getContainerLog(long projectId) throws IOException;
}
