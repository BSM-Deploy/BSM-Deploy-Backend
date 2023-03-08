package bssm.deploy.global.error.exceptions;

import bssm.deploy.global.error.GeneralException;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ConflictException extends GeneralException {

    private final int statusCode = 409;
    private String message = "Conflict";

    public ConflictException(String message) {
        this.message = message;
    }
}