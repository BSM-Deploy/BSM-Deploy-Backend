package bssm.deploy.domain.project.presentaion.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UpdateEnvVarReq {

    @Schema(description = "프로젝트 id")
    @NotNull
    private Long projectId;

    @Schema(description = "Multi-line 환경 변수 문자열")
    @NotNull
    private String envVar;

}
