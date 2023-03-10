package bssm.deploy.domain.deploy.presentation.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class DeployProjectReq {

    @Schema(description = "프로젝트 id")
    @NotNull
    private Long projectId;

}
