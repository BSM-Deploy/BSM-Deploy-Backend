package bssm.deploy.domain.auth.presentation.dto.res;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthTokenRes {

    @Schema(title = "액세스 토큰")
    private String accessToken;

    @Schema(title = "리프레시 토큰")
    private String refreshToken;

    public static AuthTokenRes create(String accessToken, String refreshToken) {
        AuthTokenRes res = new AuthTokenRes();
        res.accessToken = accessToken;
        res.refreshToken = refreshToken;
        return res;
    }

    public static AuthTokenRes create(String accessToken) {
        AuthTokenRes res = new AuthTokenRes();
        res.accessToken = accessToken;
        return res;
    }
}
