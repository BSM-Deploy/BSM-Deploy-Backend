package bssm.deploy.domain.auth.presentation.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class AuthTokenRefreshReq {

    @NotBlank
    @Schema(description = "리프레시 토큰")
    private String refreshToken;

}
