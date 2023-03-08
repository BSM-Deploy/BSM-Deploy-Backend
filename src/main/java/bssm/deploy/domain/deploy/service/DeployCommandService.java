package bssm.deploy.domain.deploy.service;

import java.io.File;
import java.io.IOException;

public interface DeployCommandService {

    void extractZipFile(File fromZipFile, File toDir) throws IOException;

    void removeDir(File dir) throws IOException;
}
