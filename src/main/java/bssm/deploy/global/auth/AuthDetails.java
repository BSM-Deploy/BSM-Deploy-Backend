package bssm.deploy.global.auth;

import bssm.deploy.domain.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@NoArgsConstructor
public class AuthDetails implements UserDetails {

    private String username;
    private User user;

    public static AuthDetails ofUser(User user) {
        AuthDetails authDetails = new AuthDetails();
        authDetails.username = String.valueOf(user.getId());
        authDetails.user = user;
        return authDetails;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        if (user != null) {
            authorities.add(user.getAuthority().toAuthority());
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return username;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
