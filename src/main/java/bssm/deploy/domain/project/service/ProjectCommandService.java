package bssm.deploy.domain.project.service;

import java.io.File;

public interface ProjectCommandService {

    void extractZipFile(File fromZipFile, File toDir) throws Exception;

    void removeDir(File dir) throws Exception;

    void moveFile(File from, File to) throws Exception;
}
