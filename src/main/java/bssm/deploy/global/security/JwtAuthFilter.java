package bssm.deploy.global.security;

import bssm.deploy.global.auth.AuthDetails;
import bssm.deploy.global.auth.AuthDetailsService;
import bssm.deploy.global.jwt.JwtResolver;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtResolver jwtResolver;
    private final AuthDetailsService authDetailsService;

    @Value("${token.access-token.name}")
    private String ACCESS_TOKEN_NAME;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        authentication(req);
        filterChain.doFilter(req, res);
    }

    private void authentication(HttpServletRequest req) {
        String token = req.getHeader(ACCESS_TOKEN_NAME);
        if (token == null) return;

        AuthDetails userDetails = getAuthDetails(token);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private AuthDetails getAuthDetails(String token) {
        return authDetailsService.loadUser(jwtResolver.getUserId(token));
    }

}

