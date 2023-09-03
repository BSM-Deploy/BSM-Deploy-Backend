package bssm.deploy.domain.user.domain.type;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum UserAuthority {
    USER,
    ADMIN;

    public GrantedAuthority toAuthority() {
        return new SimpleGrantedAuthority(this.getRole());
    }

    public String getRole() {
        return "ROLE_" + this.name();
    }
}
