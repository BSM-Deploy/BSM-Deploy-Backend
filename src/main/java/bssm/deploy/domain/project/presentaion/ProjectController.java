package bssm.deploy.domain.project.presentaion;

import bssm.deploy.domain.project.presentaion.dto.req.CreateProjectReq;
import bssm.deploy.domain.project.presentaion.dto.req.UploadProjectReq;
import bssm.deploy.domain.project.presentaion.dto.res.ProjectRes;
import bssm.deploy.domain.project.service.ProjectService;
import bssm.deploy.global.dto.ListRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Tag(name = "프로젝트")
@RestController
@RequestMapping("project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @Operation(summary = "프로젝트 생성")
    @PostMapping
    public void createProject(@Valid @RequestBody CreateProjectReq req) {
        projectService.createProject(req);
    }

    @Operation(summary = "프로젝트 파일 업로드")
    @PutMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public void uploadProject(@ModelAttribute UploadProjectReq req) throws IOException {
        projectService.uploadProject(req);
    }

    @Operation(summary = "프로젝트 리스트 보기")
    @GetMapping
    public ListRes<ProjectRes> findProjectList() {
        return projectService.findProjectList();
    }
}
