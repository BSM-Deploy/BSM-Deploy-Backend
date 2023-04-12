package bssm.deploy.domain.project.exception;

import bssm.deploy.global.error.exceptions.NotFoundException;

public class NoSuchProjectFileException extends NotFoundException {
    public NoSuchProjectFileException() {
        super("프로젝트 파일을 찾을 수 없습니다.");
    }
}