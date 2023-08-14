package bssm.deploy.domain.project.exception;

import bssm.deploy.global.error.exceptions.ForbiddenException;

public class MaxProjectsExceededException extends ForbiddenException {
    public MaxProjectsExceededException(short maxContainerProjects) {
        super("동적 프로젝트의 최대 갯수는 " + maxContainerProjects + "개 입니다.");
    }
}