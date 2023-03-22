package bssm.deploy.domain.project.service;

import bssm.deploy.domain.project.domain.type.ProjectType;
import bssm.deploy.global.error.exceptions.InternalServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@RequiredArgsConstructor
public class ProcessProjectFileService {

    private final LinuxProjectCommandService projectCommandService;

    public File processProjectFile(File tempProjectFile, File projectDir, ProjectType projectType) throws Exception {
        if (projectType.equals(ProjectType.SINGLE_HTML)) {
            return processSingleHtmlFile(tempProjectFile, projectDir);
        }
        if (projectType.equals(ProjectType.MULTIPLE_FILE)) {
            return processMultipleFile(tempProjectFile, projectDir);
        }
        if (projectType.equals(ProjectType.BUILT_REACT_JS)) {
            return processReactJsFile(tempProjectFile, projectDir);
        }
        if (projectType.equals(ProjectType.BUILT_NEXT_JS)) {
            return processNextJsFile(tempProjectFile, projectDir);
        }
        throw new InternalServerException();
    }

    private File processSingleHtmlFile(File tempProjectFile, File projectDir) throws Exception {
        File newFile = new File(projectDir.getAbsoluteFile() + "/index.html");
        projectCommandService.moveFile(tempProjectFile, newFile);
        return newFile;
    }

    private File processMultipleFile(File tempProjectZipFile, File projectDir) throws Exception {
        projectCommandService.extractZipFile(tempProjectZipFile, projectDir);
        return projectDir;
    }

    private File processReactJsFile(File tempProjectZipFile, File projectDir) throws Exception {
        projectCommandService.extractZipFile(tempProjectZipFile, projectDir);
        return projectDir;
    }

    private File processNextJsFile(File tempProjectZipFile, File projectDir) throws Exception {
        projectCommandService.extractZipFile(tempProjectZipFile, projectDir);
        return projectDir;
    }
}
