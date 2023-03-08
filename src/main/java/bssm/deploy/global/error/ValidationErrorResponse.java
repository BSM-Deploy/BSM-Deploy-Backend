package bssm.deploy.global.error;

import lombok.Getter;

import java.util.Map;

@Getter
public class ValidationErrorResponse {

    private final int statusCode = 400;
    private final Map<String, String> fields;

    public ValidationErrorResponse(Map<String, String> fields) {
        this.fields = fields;
    }
}
