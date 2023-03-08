package bssm.deploy.domain.auth.facade;

import bssm.deploy.domain.auth.domain.RefreshToken;
import bssm.deploy.domain.auth.domain.repository.RefreshTokenRepository;
import bssm.deploy.domain.auth.exception.NoSuchRefreshTokenException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthFacade {

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken getRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByTokenAndIsAvailable(refreshToken, true)
                .orElseThrow(NoSuchRefreshTokenException::new);
    }
}
