package bssm.deploy.domain.auth.presentation;

import bssm.deploy.domain.auth.presentation.dto.req.AuthTokenRefreshReq;
import bssm.deploy.domain.auth.presentation.dto.req.BsmOAuthReq;
import bssm.deploy.domain.auth.presentation.dto.res.AuthTokenRes;
import bssm.deploy.domain.auth.service.AuthService;
import bssm.deploy.domain.auth.service.oauth.OauthBsmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "인증")
@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final OauthBsmService oauthBsmService;

    @Value("${token.refresh-token.name}")
    private String REFRESH_TOKEN_NAME;


    @Operation(summary = "jwt 리프레시")
    @PutMapping("token/refresh")
    public AuthTokenRes authTokenRefresh(@Validated @RequestBody AuthTokenRefreshReq req) {
        return authService.authTokenRefresh(req);
    }


    @Operation(summary = "로그아웃 (헤더에 리프레시 토큰 넣어주세요)")
    @DeleteMapping("logout")
    public void logout(HttpServletRequest req) {
        authService.logout(req.getHeader(REFRESH_TOKEN_NAME));
    }

    @Operation(summary = "BSM OAuth 로그인")
    @PostMapping("/oauth/bsm")
    public AuthTokenRes bsmOauth(@Validated @RequestBody BsmOAuthReq req) throws Exception {
        return oauthBsmService.bsmOauthLogin(req);
    }

}
