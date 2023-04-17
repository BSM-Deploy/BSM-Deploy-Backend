package bssm.deploy.domain.project.presentaion;

import bssm.deploy.domain.project.presentaion.dto.req.CreateProjectReq;
import bssm.deploy.domain.project.presentaion.dto.req.UploadProjectReq;
import bssm.deploy.domain.project.presentaion.dto.res.ProjectRes;
import bssm.deploy.domain.project.service.ProjectService;
import bssm.deploy.global.dto.ListRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Tag(name = "프로젝트")
@RestController
@RequestMapping("project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @Operation(summary = "내 프로젝트 리스트 조회")
    @GetMapping
    public ListRes<ProjectRes> findProjectList() {
        return projectService.findProjectList();
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", content = @Content)
    })
    @Operation(summary = "내 프로젝트 단건 조회")
    @GetMapping("{projectId}")
    public ProjectRes findProject(@PathVariable Long projectId) {
        return projectService.findProject(projectId);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "요청 잘못됨, 이미 존재하는 도메인 접두사", content = @Content),
            @ApiResponse(responseCode = "500", content = @Content)
    })
    @Operation(summary = "프로젝트 생성")
    @PostMapping
    public ProjectRes createProject(@Valid @RequestBody CreateProjectReq req) {
        return projectService.createProject(req);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "파일 확장자가 올바르지 않음"),
            @ApiResponse(responseCode = "404", description = "프로젝트를 찾을 수 없음"),
            @ApiResponse(responseCode = "500")
    })
    @Operation(summary = "프로젝트 업로드", description = "file 속성에는 SINGLE_HTML이면 html 파일만, 나머지는 압축된 zip 파일")
    @PutMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public void uploadProject(@ModelAttribute UploadProjectReq req) throws Exception {
        projectService.uploadProject(req);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "프로젝트를 찾을 수 없음"),
            @ApiResponse(responseCode = "500")
    })
    @Operation(summary = "프로젝트 삭제")
    @DeleteMapping("{projectId}")
    public void deleteProject(@PathVariable Long projectId) throws Exception {
        projectService.deleteProject(projectId);
    }

}
