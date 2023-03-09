package bssm.deploy.domain.project.service;

import java.io.File;
import java.io.IOException;

public interface ProjectCommandService {

    void extractZipFile(File fromZipFile, File toDir) throws IOException;

    void removeDir(File dir) throws IOException;
}
