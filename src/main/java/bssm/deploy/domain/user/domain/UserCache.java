package bssm.deploy.domain.user.domain;

import bssm.deploy.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "user")
public class UserCache extends BaseTimeEntity {

    @Id
    private Long id;
    private String nickname;
    private String profileImg;

    public static UserCache create(User user) {
        UserCache userCache = new UserCache();
        userCache.id = user.getId();
        userCache.nickname = user.getNickname();
        userCache.profileImg = user.getProfileImg();
        return userCache;
    }

    public User toUser() {
        return User.create(this);
    }

}
