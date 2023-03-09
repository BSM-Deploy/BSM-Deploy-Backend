package bssm.deploy.global.utils;

import bssm.deploy.global.error.exceptions.NotMatchedExtensionException;
import lombok.experimental.UtilityClass;
import org.apache.commons.io.FilenameUtils;

import java.util.List;

@UtilityClass
public class FileValidateUtil {

    public void validateExtension(String filename, String allowExtension) {
        if (!FilenameUtils.getExtension(filename).equals(allowExtension)) {
            throw new NotMatchedExtensionException();
        }
    }

    public void validateExtension(String filename, List<String> allowExtensionList) {
        String fileExtension = FilenameUtils.getExtension(filename);
        boolean valid = allowExtensionList.stream()
                .anyMatch(extension -> fileExtension.equals(extension));
        if (!valid) {
            throw new NotMatchedExtensionException();
        }
    }
}
