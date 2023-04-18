package bssm.deploy.domain.deploy.service;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteWatchdog;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

import static bssm.deploy.domain.deploy.constant.ScriptFileConstant.*;

@Service
public class LinuxDeployCommandService implements DeployCommandService {

    @Value("${bsm-deploy.script-path.base}")
    private String SCRIPT_BASE_PATH;

    private static final long MAX_DEPLOY_TIME = 300_000;

    public void deploySingleHtml(long projectId, String domainPrefix) throws IOException {
        CommandLine command = CommandLine.parse("sh " + DEPLOY_SINGLE_HTML + " " + projectId + " " + domainPrefix);
        DefaultExecutor executor = new DefaultExecutor();
        executor.setWorkingDirectory(new File(SCRIPT_BASE_PATH));
        executor.execute(command);
    }

    public void deployMultipleFile(long projectId, String domainPrefix) throws IOException {
        CommandLine command = CommandLine.parse("sh " + DEPLOY_MULTIPLE_FILE + " " + projectId + " " + domainPrefix);
        DefaultExecutor executor = new DefaultExecutor();
        executor.setWorkingDirectory(new File(SCRIPT_BASE_PATH));
        executor.execute(command);
    }

    public void deployReactJs(long projectId, String domainPrefix) throws IOException {
        CommandLine command = CommandLine.parse("sh " + DEPLOY_REACT_JS + " " + projectId + " " + domainPrefix);
        DefaultExecutor executor = new DefaultExecutor();
        executor.setWorkingDirectory(new File(SCRIPT_BASE_PATH));
        executor.execute(command);
    }

    public void deployNextJs(long projectId, String domainPrefix) throws IOException {
        CommandLine command = CommandLine.parse("sh " + DEPLOY_NEXT_JS + " " + projectId + " " + domainPrefix);
        DefaultExecutor executor = new DefaultExecutor();
        executor.setWorkingDirectory(new File(SCRIPT_BASE_PATH));
        executor.setWatchdog(new ExecuteWatchdog(MAX_DEPLOY_TIME));
        executor.execute(command);
    }

}
