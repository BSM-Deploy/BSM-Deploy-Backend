package bssm.deploy.domain.deploy.service;

import java.io.IOException;

public interface DeployCommandService {

    void deploySingleHtml(long projectId, String domainPrefix) throws IOException;
}
