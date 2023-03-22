package bssm.deploy.domain.project.service;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class LinuxProjectCommandService implements ProjectCommandService {

    public void extractZipFile(File fromZipFile, File toDir) throws Exception {
        CommandLine command = CommandLine.parse("unzip " + fromZipFile.getAbsolutePath() + " -d ./");
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
        CommandLine command = CommandLine.parse("mv . " + to.getAbsolutePath());
        DefaultExecutor executor = new DefaultExecutor();
        executor.setWorkingDirectory(from);
        executor.execute(command);
    }
}
