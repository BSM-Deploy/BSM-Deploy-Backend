package bssm.deploy.global.error.exceptions;

import bssm.deploy.global.error.GeneralException;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NotFoundException extends GeneralException {

    private final int statusCode = 404;
    private String message = "Not Found";

    public NotFoundException(String message) {
        this.message = message;
    }
}