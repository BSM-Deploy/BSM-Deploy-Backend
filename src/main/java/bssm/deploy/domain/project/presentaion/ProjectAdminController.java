package bssm.deploy.domain.project.presentaion;

import bssm.deploy.domain.project.presentaion.dto.req.admin.FindProjectListAdminReq;
import bssm.deploy.domain.project.presentaion.dto.res.ProjectRes;
import bssm.deploy.domain.project.service.ProjectAdminService;
import bssm.deploy.global.dto.ListRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "프로젝트 관리자 API")
@RestController
@RequestMapping("admin/project")
@RequiredArgsConstructor
public class ProjectAdminController {

    private final ProjectAdminService projectAdminService;

    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "403", content = @Content),
            @ApiResponse(responseCode = "404", content = @Content),
            @ApiResponse(responseCode = "500", content = @Content)
    })
    @Operation(summary = "프로젝트 단건 조회")
    @GetMapping("{projectId}")
    public ProjectRes findProject(@PathVariable Long projectId) {
        return projectAdminService.findProject(projectId);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "403", content = @Content),
            @ApiResponse(responseCode = "500", content = @Content)
    })
    @Operation(summary = "프로젝트 리스트 조회")
    @GetMapping
    public ListRes<ProjectRes> findProjectList(@Validated @ParameterObject @ModelAttribute FindProjectListAdminReq req) {
        return projectAdminService.findProjectList(req);
    }

}
