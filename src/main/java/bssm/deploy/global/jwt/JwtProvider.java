package bssm.deploy.global.jwt;

import bssm.deploy.domain.auth.domain.RefreshToken;
import bssm.deploy.domain.auth.domain.repository.RefreshTokenRepository;
import bssm.deploy.domain.auth.presentation.dto.res.AuthTokenRes;
import bssm.deploy.domain.user.domain.User;
import bssm.deploy.domain.user.facade.UserFacade;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final UserFacade userFacade;
    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${jwt.secretKey}")
    private String JWT_SECRET_KEY;
    @Value("${jwt.expire-time.accessToken}")
    private long JWT_TOKEN_MAX_TIME;
    @Value("${jwt.expire-time.refreshToken}")
    private long JWT_REFRESH_TOKEN_MAX_TIME;

    public AuthTokenRes createAuthTokenRes(User user) {
        return AuthTokenRes.create(
                createAccessToken(user),
                createRefreshToken(user)
        );
    }

    public String createAccessToken(User user) {
        userFacade.saveUserCache(user);

        Claims claims = Jwts.claims();
        claims.put("id", user.getId());
        return createToken(claims, JWT_TOKEN_MAX_TIME);
    }

    public String createRefreshToken(User user) {
        RefreshToken refreshToken = RefreshToken.create(user);
        refreshTokenRepository.save(refreshToken);

        Claims claims = Jwts.claims();
        claims.put("token", refreshToken.getToken());
        return createToken(claims, JWT_REFRESH_TOKEN_MAX_TIME);
    }

    private String createToken(Claims claims, long time) {
        Date date = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(date)
                .setExpiration(new Date(date.getTime() + (time * 1000)))
                .signWith(Keys.hmacShaKeyFor(JWT_SECRET_KEY.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
                .compact();
    }

}
