package bssm.deploy.domain.project.presentaion.dto.req;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class UploadProjectReq {

    @Schema(description = "프로젝트 id")
    @NotNull
    private Long projectId;

    @Schema(description = "SINGLE_HTML이면 html 파일만, 나머지는 압축된 zip 파일")
    @NotNull
    private MultipartFile file;

    @Valid
    @NotNull
    @JsonSerialize
    private CreateProjectReq createReq;

}
