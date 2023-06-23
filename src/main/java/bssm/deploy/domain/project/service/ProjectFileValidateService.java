package bssm.deploy.domain.project.service;

import bssm.deploy.domain.project.domain.type.ProjectType;
import bssm.deploy.global.error.exceptions.InternalServerException;
import bssm.deploy.global.utils.FileValidateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static bssm.deploy.domain.project.constant.ProjectFileConstant.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProjectFileValidateService {

    private static final List<ProjectType> zipFileProjects = List.of(new ProjectType[]{
            ProjectType.MULTIPLE_FILE,
            ProjectType.BUILT_REACT_JS,
            ProjectType.BUILT_NEXT_JS,
            ProjectType.BUILT_NODE_JS
    });

    public void validateFile(MultipartFile file, ProjectType projectType) {
        if (zipFileProjects.contains(projectType)) {
            FileValidateUtil.validateExtension(file.getOriginalFilename(), ZIP);
            return;
        }
        if (projectType.equals(ProjectType.SINGLE_HTML)) {
            FileValidateUtil.validateExtension(file.getOriginalFilename(), HTML);
            return;
        }
        if (projectType.equals(ProjectType.BUILT_SPRING_JAR)) {
            FileValidateUtil.validateExtension(file.getOriginalFilename(), JAR);
            return;
        }
        throw new InternalServerException();
    }

}
