package bssm.deploy.domain.deploy.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

import static bssm.deploy.domain.deploy.constant.ScriptFileConstant.*;

@Service
public class LinuxDeployCommandService implements DeployCommandService {

    @Value("${bsm-deploy.script-path.base}")
    private String SCRIPT_BASE_PATH;

    public void deploySingleHtml(long projectId, String domainPrefix) throws IOException {
        ProcessBuilder builder = new ProcessBuilder();
        builder.directory(new File(SCRIPT_BASE_PATH));
        builder.command("sh", DEPLOY_SINGLE_HTML, String.valueOf(projectId), domainPrefix);
        builder.start();
    }

    public void deployMultipleFile(long projectId, String domainPrefix) throws IOException {
        ProcessBuilder builder = new ProcessBuilder();
        builder.directory(new File(SCRIPT_BASE_PATH));
        builder.command("sh", DEPLOY_MULTIPLE_FILE, String.valueOf(projectId), domainPrefix);
        builder.start();
    }

    public void deployReactJs(long projectId, String domainPrefix) throws IOException {
        ProcessBuilder builder = new ProcessBuilder();
        builder.directory(new File(SCRIPT_BASE_PATH));
        builder.command("sh", DEPLOY_REACT_JS, String.valueOf(projectId), domainPrefix);
        builder.start();
    }

    public void deployNextJs(long projectId, String domainPrefix) throws IOException {
        ProcessBuilder builder = new ProcessBuilder();
        builder.directory(new File(SCRIPT_BASE_PATH));
        builder.command("sh", DEPLOY_NEXT_JS, String.valueOf(projectId), domainPrefix);
        builder.start();
    }

}
