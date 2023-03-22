package bssm.deploy.domain.deploy.service;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
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
        CommandLine command = CommandLine.parse("sh " + CANCEL_DEPLOY_SINGLE_HTML + " " + projectId);
        DefaultExecutor executor = new DefaultExecutor();
        executor.setWorkingDirectory(new File(SCRIPT_BASE_PATH));
        executor.execute(command);
    }

    public void cancelDeployMultipleFile(long projectId) throws IOException {
        CommandLine command = CommandLine.parse("sh " + CANCEL_DEPLOY_MULTIPLE_FILE + " " + projectId);
        DefaultExecutor executor = new DefaultExecutor();
        executor.setWorkingDirectory(new File(SCRIPT_BASE_PATH));
        executor.execute(command);
    }

    public void cancelDeployReactJs(long projectId) throws IOException {
        CommandLine command = CommandLine.parse("sh " + CANCEL_DEPLOY_REACT_JS + " " + projectId);
        DefaultExecutor executor = new DefaultExecutor();
        executor.setWorkingDirectory(new File(SCRIPT_BASE_PATH));
        executor.execute(command);
    }

    public void cancelDeployNextJs(long projectId) throws IOException {
        CommandLine command = CommandLine.parse("sh " + CANCEL_DEPLOY_NEXT_JS + " " + projectId);
        DefaultExecutor executor = new DefaultExecutor();
        executor.setWorkingDirectory(new File(SCRIPT_BASE_PATH));
        executor.execute(command);
    }

}
