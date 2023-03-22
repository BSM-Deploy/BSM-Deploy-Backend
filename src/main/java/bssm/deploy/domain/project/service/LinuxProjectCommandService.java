package bssm.deploy.domain.project.service;

import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class LinuxProjectCommandService implements ProjectCommandService {

    public void extractZipFile(File fromZipFile, File toDir) throws Exception {
        ProcessBuilder builder = new ProcessBuilder();
        builder.directory(toDir);
        builder.command("unzip", fromZipFile.getAbsolutePath(), "-d", "./");
        builder.start()
                .waitFor();
    }

    public void removeDir(File dir) throws Exception {
        ProcessBuilder builder = new ProcessBuilder();
        builder.directory(dir);
        builder.command("rm", "-rf", "./");
        builder.start()
                .waitFor();
    }

    public void moveFile(File from, File to) throws Exception {
        ProcessBuilder builder = new ProcessBuilder();
        builder.command("mv", from.getAbsolutePath(), to.getAbsolutePath());
        builder.start()
                .waitFor();
    }
}
