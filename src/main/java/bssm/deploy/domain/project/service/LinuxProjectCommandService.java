package bssm.deploy.domain.project.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class LinuxProjectCommandService implements ProjectCommandService {

    public void extractZipFile(File fromZipFile, File toDir) throws IOException {
        ProcessBuilder builder = new ProcessBuilder();
        builder.directory(toDir);
        builder.command("unzip", fromZipFile.getAbsolutePath(), "-d", "./");
        builder.start();
    }

    public void removeDir(File dir) throws IOException {
        ProcessBuilder builder = new ProcessBuilder();
        builder.directory(dir);
        builder.command("rm", "-rf", "./");
        builder.start();
    }

    public void moveFile(File from, File to) throws IOException {
        ProcessBuilder builder = new ProcessBuilder();
        builder.command("mv", from.getAbsolutePath(), to.getAbsolutePath());
        builder.start();
    }
}
