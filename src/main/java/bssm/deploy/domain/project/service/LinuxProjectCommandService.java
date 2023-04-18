package bssm.deploy.domain.project.service;

import bssm.deploy.domain.project.domain.type.ProjectType;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

import static bssm.deploy.domain.project.constant.ScriptFileConstant.*;

@Service
public class LinuxProjectCommandService implements ProjectCommandService {

    @Value("${bsm-deploy.script-path.base}")
    private String SCRIPT_BASE_PATH;

    public void extractZipFile(File fromZipFile, File toDir) throws Exception {
        CommandLine command = CommandLine.parse("unzip -o " + fromZipFile.getAbsolutePath() + " -d ./");
        DefaultExecutor executor = new DefaultExecutor();
        executor.setWorkingDirectory(toDir);
        executor.execute(command);
    }

    public void removeDir(File dir) throws Exception {
        CommandLine command = CommandLine.parse("rm -rf " + dir.getAbsolutePath());
        DefaultExecutor executor = new DefaultExecutor();
        executor.execute(command);
    }

    public void moveFile(File from, File to) throws Exception {
        CommandLine command = CommandLine.parse("mv " + from.getAbsolutePath() + " " + to.getAbsolutePath());
        DefaultExecutor executor = new DefaultExecutor();
        executor.execute(command);
    }

    public void createProject(long projectId, ProjectType projectType) throws IOException {
        CommandLine command = CommandLine.parse("sh " + CREATE_PROJECT + " " + projectId + " " + projectType.name());
        DefaultExecutor executor = new DefaultExecutor();
        executor.setWorkingDirectory(new File(SCRIPT_BASE_PATH));
        executor.execute(command);
    }
}
