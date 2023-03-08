package bssm.deploy.global.error.exceptions;

import bssm.deploy.global.error.GeneralException;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UnAuthorizedException extends GeneralException {

    private final int statusCode = 401;
    private String message = "UnAuthorized";

    public UnAuthorizedException(String message) {
        this.message = message;
    }
}