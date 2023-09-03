package bssm.deploy.domain.user.domain;

import bssm.deploy.domain.user.domain.type.UserAuthority;
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

    @Column(nullable = false, length = 12)
    @Enumerated(EnumType.STRING)
    private UserAuthority authority;

    @Column(name = "max_container_projects", nullable = false)
    private Short maxContainerProjects;

    public static User create(UserCache userCache) {
        User user = new User();
        user.id = userCache.getId();
        user.nickname = userCache.getNickname();
        user.profileImg = userCache.getProfileImg();
        user.maxContainerProjects = userCache.getMaxContainerProjects();
        user.authority = userCache.getAuthority();
        return user;
    }

    public static User ofNormal(Long userCode, String nickname, String profileImg) {
        User user = new User();
        user.id = userCode;
        user.nickname = nickname;
        user.profileImg = profileImg;
        user.authority = UserAuthority.USER;
        user.maxContainerProjects = 5;
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
