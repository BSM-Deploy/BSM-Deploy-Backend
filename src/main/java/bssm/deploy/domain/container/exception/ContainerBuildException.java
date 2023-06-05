package bssm.deploy.domain.container.exception;

import bssm.deploy.global.error.exceptions.InternalServerException;

public class ContainerBuildException extends InternalServerException {
    public ContainerBuildException(String errorLog) {
        super(errorLog);
    }
}