package bssm.deploy.global.error.exceptions;

import com.google.common.collect.ImmutableMap;

public class NotMatchedExtensionException extends BadRequestException {
    public NotMatchedExtensionException() {
        super(ImmutableMap.<String, String>builder().
                put("fileExtension", "파일 확장자가 올바르지 않습니다.").
                build());
    }
}
