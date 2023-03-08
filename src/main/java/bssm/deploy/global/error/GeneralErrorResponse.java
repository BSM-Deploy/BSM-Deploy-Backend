package bssm.deploy.global.error;

import lombok.Getter;

@Getter
public class GeneralErrorResponse {

    private final int statusCode;
    private final String message;

    public GeneralErrorResponse(GeneralException httpError) {
        this.statusCode = httpError.getStatusCode();
        this.message = httpError.getMessage();
    }
}
