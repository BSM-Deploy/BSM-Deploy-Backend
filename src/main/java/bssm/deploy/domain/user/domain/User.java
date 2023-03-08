package bssm.deploy.domain.user.domain;

import bssm.deploy.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id
    @Column(columnDefinition = "INT UNSIGNED")
    private Long id;

    @Column(nullable = false, length = 20, unique = true)
    private String nickname;

    @Column(name = "profile_img")
    private String profileImg;

    public static User create(UserCache userCache) {
        User user = new User();
        user.id = userCache.getId();
        user.nickname = userCache.getNickname();
        user.profileImg = userCache.getProfileImg();
        return user;
    }

    public static User ofNormal(Long userCode, String nickname, String profileImg) {
        User user = new User();
        user.id = userCode;
        user.nickname = nickname;
        user.profileImg = profileImg;
        return user;
    }

    public void update(String nickname, String profileImg) {
        this.nickname = nickname;
        this.profileImg = profileImg;
    }

    public UserCache toUserCache() {
        return UserCache.create(this);
    }

}
