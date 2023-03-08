package bssm.deploy.global.auth;

import bssm.deploy.global.error.GeneralErrorResponse;
import bssm.deploy.global.error.GeneralException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthFilterExceptionHandler extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(req, res);
        } catch (GeneralException e) {
            exceptionHandler(res, e);
        }
    }

    private void exceptionHandler(HttpServletResponse res, GeneralException exception) {
        res.setStatus(exception.getStatusCode());
        res.setContentType("application/json;charset=UTF-8");
        try {
            res.getWriter().write(objectMapper.writeValueAsString(new GeneralErrorResponse(exception)));
            res.getWriter().flush();
            res.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
