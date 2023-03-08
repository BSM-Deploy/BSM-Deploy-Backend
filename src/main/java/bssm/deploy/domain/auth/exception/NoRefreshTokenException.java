package bssm.deploy.domain.auth.exception;

import bssm.deploy.global.error.exceptions.BadRequestException;
import com.google.common.collect.ImmutableMap;

public class NoRefreshTokenException extends BadRequestException {
    public NoRefreshTokenException() {
        super(ImmutableMap.<String, String>builder().
                put("refreshToken", "리프레시 토큰이 없습니다.").
                build());
    }
}