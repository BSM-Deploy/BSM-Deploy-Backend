package bssm.deploy.domain.project.presentaion;

import bssm.deploy.domain.project.presentaion.dto.req.CreateProjectReq;
import bssm.deploy.domain.project.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("deploy")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService deployService;

    @Operation(summary = "프로젝트 생성")
    @PostMapping
    public void createProject(@Valid @RequestBody CreateProjectReq req) {
        deployService.createProject(req);
    }

}
