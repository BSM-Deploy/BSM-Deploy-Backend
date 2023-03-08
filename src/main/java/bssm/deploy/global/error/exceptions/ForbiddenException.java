package bssm.deploy.global.error.exceptions;

import bssm.deploy.global.error.GeneralException;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ForbiddenException extends GeneralException {

    private final int statusCode = 403;
    private String message = "Forbidden";

    public ForbiddenException(String message) {
        this.message = message;
    }
}