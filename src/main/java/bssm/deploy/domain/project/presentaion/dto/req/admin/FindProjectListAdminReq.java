package bssm.deploy.domain.project.presentaion.dto.req.admin;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindProjectListAdminReq {

    @Schema(description = "유저 id", nullable = true, type = "integer", format = "int64")
    private Long userId;

    @Schema(description = "최근 날짜순으로 정렬", type = "boolean")
    private boolean orderRecent;

}
