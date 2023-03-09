package bssm.deploy.domain.project.service;

import bssm.deploy.domain.project.domain.type.ProjectType;
import bssm.deploy.global.error.exceptions.InternalServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ProcessProjectFileService {

    private final LinuxProjectCommandService projectCommandService;

    public void processProjectFile(File tempProjectFile, File projectDir, ProjectType projectType) throws IOException {
        if (projectType.equals(ProjectType.SINGLE_HTML)) {
            processSingleHtmlFile(tempProjectFile, projectDir);
            return;
        }
        if (projectType.equals(ProjectType.MULTIPLE_FILE)) {

            return;
        }
        if (projectType.equals(ProjectType.BUILT_REACT_JS)) {

            return;
        }
        if (projectType.equals(ProjectType.BUILT_NEXT_JS)) {

            return;
        }
        throw new InternalServerException();
    }

    private void processSingleHtmlFile(File tempProjectFile, File projectDir) throws IOException {
        File newFile = new File(projectDir.getAbsoluteFile() + "/index.html");
        projectCommandService.moveFile(tempProjectFile, newFile);
    }
}
