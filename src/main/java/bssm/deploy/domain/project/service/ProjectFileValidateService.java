package bssm.deploy.domain.project.service;

import bssm.deploy.domain.project.domain.type.ProjectType;
import bssm.deploy.global.error.exceptions.InternalServerException;
import bssm.deploy.global.utils.FileValidateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import static bssm.deploy.domain.project.constant.ProjectFileConstant.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProjectFileValidateService {

    public void validateFile(MultipartFile file, ProjectType projectType) {
        if (projectType.equals(ProjectType.SINGLE_HTML)) {
            validateSingleHtml(file);
            return;
        }
        if (projectType.equals(ProjectType.MULTIPLE_FILE)) {
            validateSingleHtml(file);
            return;
        }
        if (projectType.equals(ProjectType.BUILT_REACT_JS)) {
            validateSingleHtml(file);
            return;
        }
        if (projectType.equals(ProjectType.BUILT_NEXT_JS)) {
            validateSingleHtml(file);
            return;
        }
        throw new InternalServerException();
    }

    private void validateSingleHtml(MultipartFile file) {
        FileValidateUtil.validateExtension(file.getOriginalFilename(), HTML);
    }

    private void validateMultipleHtml(MultipartFile file) {
        FileValidateUtil.validateExtension(file.getOriginalFilename(), ZIP);
    }

    private void validateBuiltReactJs(MultipartFile file) {
        FileValidateUtil.validateExtension(file.getOriginalFilename(), ZIP);
    }

    private void validateBuiltNextJs(MultipartFile file) {
        FileValidateUtil.validateExtension(file.getOriginalFilename(), ZIP);
    }
}
