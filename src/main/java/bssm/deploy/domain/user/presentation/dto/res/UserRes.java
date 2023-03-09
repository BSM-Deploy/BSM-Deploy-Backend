package bssm.deploy.domain.user.presentation.dto.res;

import bssm.deploy.domain.user.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRes {

    @Schema(title = "유저 식별 id")
    private Long id;

    @Schema(title = "닉네임")
    private String nickname;

    @Schema(title = "프로필 사진 url", nullable = true)
    private String profileImg;

    public static UserRes create(User user) {
        UserRes userRes = new UserRes();
        userRes.id = user.getId();
        userRes.nickname = user.getNickname();
        userRes.profileImg = user.getProfileImg();
        return userRes;
    }
}
