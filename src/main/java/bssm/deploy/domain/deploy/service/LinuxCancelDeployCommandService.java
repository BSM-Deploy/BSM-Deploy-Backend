package bssm.deploy.domain.deploy.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

import static bssm.deploy.domain.deploy.constant.ScriptFileConstant.*;

@Service
public class LinuxCancelDeployCommandService implements CancelDeployCommandService {

    @Value("${bsm-deploy.script-path.base}")
    private String SCRIPT_BASE_PATH;

    public void cancelDeploySingleHtml(long projectId) throws IOException {
        ProcessBuilder builder = new ProcessBuilder();
        builder.directory(new File(SCRIPT_BASE_PATH));
        builder.command("sh", CANCEL_DEPLOY_SINGLE_HTML, String.valueOf(projectId));
        builder.start();
    }

    public void cancelDeployMultipleFile(long projectId) throws IOException {
        ProcessBuilder builder = new ProcessBuilder();
        builder.directory(new File(SCRIPT_BASE_PATH));
        builder.command("sh", CANCEL_DEPLOY_MULTIPLE_FILE, String.valueOf(projectId));
        builder.start();
    }

    public void cancelDeployReactJs(long projectId) throws IOException {
        ProcessBuilder builder = new ProcessBuilder();
        builder.directory(new File(SCRIPT_BASE_PATH));
        builder.command("sh", CANCEL_DEPLOY_REACT_JS, String.valueOf(projectId));
        builder.start();
    }

}
