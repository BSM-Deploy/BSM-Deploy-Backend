package bssm.deploy.domain.auth.presentation.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class BsmOAuthReq {

    @NotEmpty
    @Schema(title = "BSM OAuth 인증 코드")
    private String code;

}
