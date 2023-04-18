package bssm.deploy.domain.container.service;

import org.apache.commons.exec.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;

import static bssm.deploy.domain.container.constant.ScriptFileConstant.*;

@Service
public class LinuxContainerCommandService implements ContainerCommandService {

    @Value("${bsm-deploy.script-path.base}")
    private String SCRIPT_BASE_PATH;

    private static final long MAX_REBUILD_TIME = 300_000;

    public String getContainerLog(long projectId) throws IOException {
        ProcessBuilder pb = new ProcessBuilder();
        pb.directory(new File(SCRIPT_BASE_PATH));
        pb.command("sh", CONTAINER_LOG, String.valueOf(projectId));
        pb.redirectErrorStream(true);
        Process process = pb.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String output = "";
        String line;
        while ((line = reader.readLine()) != null) {
            output += line + "\n";
        }
        return output;
    }

    public void rebuildContainer(long projectId) throws IOException {
        CommandLine command = CommandLine.parse("sh " + CONTAINER_REBUILD + " " + projectId);
        DefaultExecutor executor = new DefaultExecutor();
        executor.setWorkingDirectory(new File(SCRIPT_BASE_PATH));
        executor.setWatchdog(new ExecuteWatchdog(MAX_REBUILD_TIME));
        executor.execute(command);
    }

    public void removeContainer(long projectId) throws IOException {
        CommandLine command = CommandLine.parse("sh " + CONTAINER_REMOVE + " " + projectId);
        DefaultExecutor executor = new DefaultExecutor();
        executor.setWorkingDirectory(new File(SCRIPT_BASE_PATH));
        executor.execute(command);
    }

}
