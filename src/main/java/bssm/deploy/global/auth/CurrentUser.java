package bssm.deploy.global.auth;

import bssm.deploy.domain.user.domain.User;
import bssm.deploy.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CurrentUser {

    private final UserFacade userFacade;

    public User getUser() {
        String authenticationName = SecurityContextHolder.getContext().getAuthentication().getName();
        long userId = Long.parseLong(authenticationName);
        return userFacade.findById(userId);
    }

    public User getUserOrNull() {
        String authenticationName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (authenticationName.equals("anonymousUser")) return null;
        long userId = Long.parseLong(authenticationName);
        return userFacade.findById(userId);
    }

    public User getCachedUser() {
        AuthDetails authDetails = (AuthDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return authDetails.getUser();
    }

    public User getCachedUserOrNull() {
        String authenticationName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (authenticationName.equals("anonymousUser")) return null;
        AuthDetails authDetails = (AuthDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return authDetails.getUser();
    }

}
