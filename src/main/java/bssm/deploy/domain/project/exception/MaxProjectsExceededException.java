package bssm.deploy.domain.project.exception;

import bssm.deploy.global.error.exceptions.ForbiddenException;

import static bssm.deploy.domain.project.constant.ProjectConstant.MAX_CONTAINER_PROJECTS;

public class MaxProjectsExceededException extends ForbiddenException {
    public MaxProjectsExceededException() {
        super("동적 프로젝트의 최대 갯수는 " + MAX_CONTAINER_PROJECTS + "개 입니다.");
    }
}