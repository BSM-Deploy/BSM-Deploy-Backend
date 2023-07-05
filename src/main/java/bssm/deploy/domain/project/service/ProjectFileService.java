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

import static bssm.deploy.domain.project.constant.ProjectFileConstant.ZIP_FILE_PROJECTS;

@Service
@RequiredArgsConstructor
public class ProjectFileService {

    private final LinuxProjectCommandService projectCommandService;

    @Value("${bsm-deploy.project-path.base}")
    private String PROJECT_BASE_RESOURCE_PATH;

    public void createProjectDir(Project project) throws IOException {
        projectCommandService.createProject(project.getId(), project.getProjectType());
    }

    public void uploadProjectFile(File tempProjectFile, File projectDir, ProjectType projectType) throws Exception {
        if (ZIP_FILE_PROJECTS.contains(projectType)) {
            extractZip(tempProjectFile, projectDir);
            return;
        }
        if (projectType.equals(ProjectType.SINGLE_HTML)) {
            uploadSingleHtml(tempProjectFile, projectDir);
            return;
        }
        if (projectType.equals(ProjectType.BUILT_SPRING_JAR)) {
            uploadSpringJar(tempProjectFile, projectDir);
            return;
        }
        throw new InternalServerException();
    }

    private void extractZip(File tempProjectZipFile, File projectDir) throws Exception {
        projectCommandService.extractZipFile(tempProjectZipFile, projectDir);
    }

    private void uploadSingleHtml(File tempProjectFile, File projectDir) throws Exception {
        File newFile = new File(projectDir.getAbsoluteFile() + "/index.html");
        projectCommandService.moveFile(tempProjectFile, newFile);
    }

    private void uploadSpringJar(File tempProjectFile, File projectDir) throws Exception {
        File newFile = new File(projectDir.getAbsoluteFile() + "/server.jar");
        projectCommandService.moveFile(tempProjectFile, newFile);
    }

    public void deleteProject(Project project) throws Exception {
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
