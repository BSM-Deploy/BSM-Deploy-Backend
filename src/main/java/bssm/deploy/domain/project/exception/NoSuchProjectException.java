package bssm.deploy.domain.project.exception;

import bssm.deploy.global.error.exceptions.NotFoundException;

public class NoSuchProjectException extends NotFoundException {
    public NoSuchProjectException() {
        super("프로젝트를 찾을 수 없습니다.");
    }
}