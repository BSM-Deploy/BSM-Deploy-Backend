package bssm.deploy.global.error.exceptions;

import bssm.deploy.global.error.GeneralException;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InternalServerException extends GeneralException {

    private final int statusCode = 500;
    private String message = "Internal Server Error";

    public InternalServerException(String message) {
        this.message = message;
    }
}