package bssm.deploy.domain.deploy.service;

import java.io.IOException;

public interface DeployCommandService {

    void deploySingleHtml(long projectId, String domainPrefix) throws IOException;

    void deployMultipleFile(long projectId, String domainPrefix) throws IOException;

    void deployReactJs(long projectId, String domainPrefix) throws IOException;
}
