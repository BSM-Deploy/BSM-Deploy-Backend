package bssm.deploy.global.error;

import lombok.Getter;

@Getter
public class GeneralException extends RuntimeException {

    private final int statusCode = 500;
    private final String message = "Internal Server Error";
}
