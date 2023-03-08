package bssm.deploy.domain.auth.service;

import bssm.deploy.domain.auth.exception.NoRefreshTokenException;
import bssm.deploy.domain.auth.facade.AuthFacade;
import bssm.deploy.domain.auth.presentation.dto.req.AuthTokenRefreshReq;
import bssm.deploy.domain.auth.presentation.dto.res.AuthTokenRes;
import bssm.deploy.domain.user.domain.User;
import bssm.deploy.domain.user.facade.UserFacade;
import bssm.deploy.global.jwt.JwtProvider;
import bssm.deploy.global.jwt.JwtResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final AuthFacade authFacade;
    private final UserFacade userFacade;
    private final JwtProvider jwtProvider;
    private final JwtResolver jwtResolver;

    public AuthTokenRes authTokenRefresh(AuthTokenRefreshReq req) {
        User user = userFacade.findByRefreshToken(
                jwtResolver.getRefreshToken(req.getRefreshToken())
        );
        String accessToken = jwtProvider.createAccessToken(user);
        return AuthTokenRes.create(accessToken);
    }

    @Transactional
    public void logout(String refreshToken) {
        if (refreshToken == null) {
            throw new NoRefreshTokenException();
        }

        authFacade.getRefreshToken(
                jwtResolver.getRefreshToken(refreshToken)
        ).expire();
    }

}
