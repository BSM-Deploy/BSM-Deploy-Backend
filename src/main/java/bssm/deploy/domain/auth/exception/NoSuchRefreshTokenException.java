package bssm.deploy.domain.auth.exception;

import bssm.deploy.global.error.exceptions.NotFoundException;

public class NoSuchRefreshTokenException extends NotFoundException {
    public NoSuchRefreshTokenException() {
        super("리프레시 토큰을 찾을 수 없습니다.");
    }
}