package bssm.deploy.domain.auth.exception;

import bssm.deploy.global.error.exceptions.NotFoundException;

public class NoSuchBsmAuthCodeException extends NotFoundException {
    public NoSuchBsmAuthCodeException() {
        super("BSM OAuth 인증 코드를 찾을 수 없습니다.");
    }
}