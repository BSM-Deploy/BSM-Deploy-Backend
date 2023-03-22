package bssm.deploy.domain.container.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import static bssm.deploy.domain.container.constant.ScriptFileConstant.*;

@Service
public class LinuxContainerCommandService implements ContainerCommandService {

    @Value("${bsm-deploy.script-path.base}")
    private String SCRIPT_BASE_PATH;

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
        ProcessBuilder pb = new ProcessBuilder();
        pb.directory(new File(SCRIPT_BASE_PATH));
        pb.command("sh", CONTAINER_REBUILD, String.valueOf(projectId));
        pb.start();
    }

}
