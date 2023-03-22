package bssm.deploy.domain.deploy.presentation.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CancelDeployProjectReq {

    @Schema(description = "프로젝트 id")
    @NotNull
    private Long projectId;

    public static CancelDeployProjectReq ofProjectId(Long projectId) {
        CancelDeployProjectReq req =  new CancelDeployProjectReq();
        req.projectId = projectId;
        return req;
    }

}
