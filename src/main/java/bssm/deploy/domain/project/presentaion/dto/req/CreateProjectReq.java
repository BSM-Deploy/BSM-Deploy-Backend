package bssm.deploy.domain.project.presentaion.dto.req;

import bssm.deploy.domain.project.domain.type.ProjectType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class CreateProjectReq {

    @Schema(description = "프로젝트 이름")
    @NotBlank
    @Length(min = 2, max = 16)
    private String name;

    @Schema(description = "도메인 접두사", defaultValue = "string")
    @Pattern(regexp = "^[a-zA-Z0-9-]+$")
    @Length(min = 3, max = 63)
    private String domainPrefix;

    @Schema(description = "프로젝트 파일 형식")
    @NotNull
    private ProjectType projectType;

}
