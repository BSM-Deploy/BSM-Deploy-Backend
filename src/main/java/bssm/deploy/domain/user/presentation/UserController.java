package bssm.deploy.domain.user.presentation;

import bssm.deploy.domain.user.presentation.dto.res.UserRes;
import bssm.deploy.global.auth.CurrentUser;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final CurrentUser currentUser;

    @Operation(summary = "내 계정 조회")
    @GetMapping
    public UserRes getUserInfo() {
        return UserRes.create(currentUser.getUser());
    }

}
