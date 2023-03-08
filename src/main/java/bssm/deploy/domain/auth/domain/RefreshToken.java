package bssm.deploy.domain.auth.domain;

import bssm.deploy.domain.user.domain.User;
import bssm.deploy.global.utils.SecurityUtil;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshToken {

    @Id
    @Column(length = 64)
    private String token;

    @Column(nullable = false)
    private boolean isAvailable;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Date createdAt;

    public static RefreshToken create(User user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.token = SecurityUtil.getRandomStr(64);
        refreshToken.isAvailable = true;
        refreshToken.user = user;
        refreshToken.createdAt = new Date();
        return refreshToken;
    }

    public void expire() {
        isAvailable = false;
    }

}
