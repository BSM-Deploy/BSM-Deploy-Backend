package bssm.deploy.global.config;

import leehj050211.bsmOauth.BsmOauth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class BsmOauthConfig {

    @Value("${oauth.bsm.id}")
    private String BSM_AUTH_CLIENT_ID;
    @Value("${oauth.bsm.secret}")
    private String BSM_AUTH_CLIENT_SECRET;

    @Bean("bsmOauth")
    public BsmOauth bsmOauth() {
        return new BsmOauth(BSM_AUTH_CLIENT_ID, BSM_AUTH_CLIENT_SECRET);
    }
}