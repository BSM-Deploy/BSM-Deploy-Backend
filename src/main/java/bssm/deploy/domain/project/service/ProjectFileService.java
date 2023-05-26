package bssm.deploy.domain.project.service;

import bssm.deploy.domain.project.domain.Project;
import bssm.deploy.domain.project.domain.type.ProjectType;
import bssm.deploy.global.error.exceptions.InternalServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ProjectFileService {

    private final LinuxProjectCommandService projectCommandService;

    @Value("${bsm-deploy.project-path.base}")
    private String PROJECT_BASE_RESOURCE_PATH;

    public void createProjectDir(Project project) throws IOException {
        projectCommandService.createProject(project.getId(), project.getProjectType());
    }

    public File uploadProjectFile(File tempProjectFile, File projectDir, ProjectType projectType) throws Exception {
        if (projectType.equals(ProjectType.SINGLE_HTML)) {
            return uploadSingleHtmlFile(tempProjectFile, projectDir);
        }
        if (projectType.equals(ProjectType.MULTIPLE_FILE)) {
            return uploadMultipleFile(tempProjectFile, projectDir);
        }
        if (projectType.equals(ProjectType.BUILT_REACT_JS)) {
            return uploadReactJsFile(tempProjectFile, projectDir);
        }
        if (projectType.equals(ProjectType.BUILT_NEXT_JS)) {
            return uploadNextJsFile(tempProjectFile, projectDir);
        }
        if (projectType.equals(ProjectType.BUILT_SPRING_JAR)) {
            return uploadSpringJarFile(tempProjectFile, projectDir);
        }
        throw new InternalServerException();
    }

    private File uploadSingleHtmlFile(File tempProjectFile, File projectDir) throws Exception {
        File newFile = new File(projectDir.getAbsoluteFile() + "/index.html");
        projectCommandService.moveFile(tempProjectFile, newFile);
        return newFile;
    }

    private File uploadMultipleFile(File tempProjectZipFile, File projectDir) throws Exception {
        projectCommandService.extractZipFile(tempProjectZipFile, projectDir);
        return projectDir;
    }

    private File uploadReactJsFile(File tempProjectZipFile, File projectDir) throws Exception {
        projectCommandService.extractZipFile(tempProjectZipFile, projectDir);
        return projectDir;
    }

    private File uploadNextJsFile(File tempProjectZipFile, File projectDir) throws Exception {
        projectCommandService.extractZipFile(tempProjectZipFile, projectDir);
        return projectDir;
    }

    private File uploadSpringJarFile(File tempProjectFile, File projectDir) throws Exception {
        File newFile = new File(projectDir.getAbsoluteFile() + "/server.jar");
        projectCommandService.moveFile(tempProjectFile, newFile);
        return projectDir;
    }

    public void deleteProjectFile(Project project) throws Exception {
        File projectDir = new File(PROJECT_BASE_RESOURCE_PATH + "/" + project.getId());
        projectCommandService.removeDir(projectDir);
    }

    public void applyEnvVarFile(File tempEnvVarFile, Project project) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempEnvVarFile));
        writer.write(project.getEnvVar());
        writer.close();
        projectCommandService.applyEnvVar(tempEnvVarFile, project.getId(), project.getProjectType());
    }
}
