package bssm.deploy.domain.auth.service.oauth;

import bssm.deploy.domain.auth.exception.NoSuchBsmAuthCodeException;
import bssm.deploy.domain.auth.presentation.dto.req.BsmOAuthReq;
import bssm.deploy.domain.auth.presentation.dto.res.AuthTokenRes;
import bssm.deploy.domain.user.domain.User;
import bssm.deploy.domain.user.domain.repository.UserRepository;
import bssm.deploy.domain.user.facade.UserFacade;
import bssm.deploy.global.error.exceptions.InternalServerException;
import bssm.deploy.global.jwt.JwtProvider;
import leehj050211.bsmOauth.BsmOauth;
import leehj050211.bsmOauth.dto.resource.BsmUserResource;
import leehj050211.bsmOauth.exception.BsmOAuthCodeNotFoundException;
import leehj050211.bsmOauth.exception.BsmOAuthInvalidClientException;
import leehj050211.bsmOauth.exception.BsmOAuthTokenNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class OauthBsmService {

    private final JwtProvider jwtProvider;
    private final UserFacade userFacade;
    private final BsmOauth bsmOauth;

    private final UserRepository userRepository;

    @Transactional
    public AuthTokenRes bsmOauthLogin(BsmOAuthReq req) throws IOException {

        BsmUserResource resource = getResource(req.getCode());
        User user = userFacade.findByIdOrNull(resource.getUserCode());
        user = signUpOrUpdate(user, resource);
        return jwtProvider.createAuthTokenRes(user);
    }

    private User signUpOrUpdate(User user, BsmUserResource resource) {
        if (user != null) {
            user.update(resource.getNickname(), resource.getProfileUrl());
            return user;
        }
        return signUp(resource);
    }

    private User signUp(BsmUserResource resource) {
        User user = User.ofNormal(resource.getUserCode(), resource.getNickname(), resource.getProfileUrl());
        return userRepository.save(user);
    }

    private BsmUserResource getResource(String authCode) throws IOException {
        try {
            String token = bsmOauth.getToken(authCode);
            return bsmOauth.getResource(token);
        } catch (BsmOAuthCodeNotFoundException | BsmOAuthTokenNotFoundException e) {
            throw new NoSuchBsmAuthCodeException();
        } catch (BsmOAuthInvalidClientException e) {
            throw new InternalServerException();
        }
    }

}
