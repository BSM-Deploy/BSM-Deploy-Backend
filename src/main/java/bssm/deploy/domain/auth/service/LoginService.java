package bssm.deploy.domain.auth.service;

import bssm.deploy.domain.auth.presentation.dto.res.AuthTokenRes;
import bssm.deploy.domain.user.domain.User;
import bssm.deploy.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginService {
    private final JwtProvider jwtProvider;

    public AuthTokenRes login(User user) {
        return jwtProvider.createAuthTokenRes(user);
    }

}
