package bssm.deploy.domain.project.presentaion.dto.res;

import bssm.deploy.domain.project.domain.Project;
import bssm.deploy.domain.project.domain.type.ProjectType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProjectRes {

    @Schema(description = "프로젝트 id")
    private Long id;

    @Schema(description = "프로젝트 이름")
    private String name;

    @Schema(description = "도메인 접두사")
    private String domainPrefix;

    @Schema(description = "배포여부")
    private Boolean isDeploy;

    @Schema(description = "프로젝트 유형")
    private ProjectType projectType;

    @Schema(description = "프로젝트 전체 파일 크기")
    private Long dataSize;

    public static ProjectRes create(Project project) {
        ProjectRes res = new ProjectRes();
        res.id = project.getId();
        res.name = project.getName();
        res.domainPrefix = project.getDomainPrefix();
        res.isDeploy = project.isDeploy();
        res.projectType = project.getProjectType();
        res.dataSize = project.getDataSize();
        return res;
    }
}
