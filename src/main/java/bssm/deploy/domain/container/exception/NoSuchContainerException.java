package bssm.deploy.domain.container.exception;

import bssm.deploy.global.error.exceptions.NotFoundException;

public class NoSuchContainerException extends NotFoundException {
    public NoSuchContainerException() {
        super("컨테이너를 찾을 수 없습니다.");
    }
}