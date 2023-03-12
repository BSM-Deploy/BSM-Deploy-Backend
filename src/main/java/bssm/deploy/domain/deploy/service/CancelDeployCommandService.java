package bssm.deploy.domain.deploy.service;

import java.io.IOException;

public interface CancelDeployCommandService {

    void cancelDeploySingleHtml(long projectId) throws IOException;

    void cancelDeployMultipleFile(long projectId) throws IOException;

    void cancelDeployReactJs(long projectId) throws IOException;
}
