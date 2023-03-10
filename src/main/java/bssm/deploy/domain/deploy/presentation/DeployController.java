package bssm.deploy.domain.deploy.presentation;

import bssm.deploy.domain.deploy.presentation.dto.req.DeployProjectReq;
import bssm.deploy.domain.deploy.service.DeployService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Tag(name = "배포")
@RestController
@RequestMapping("deploy")
@RequiredArgsConstructor
public class DeployController {

    private final DeployService deployService;

    @Operation(summary = "배포 하기")
    @PostMapping
    public void createProject(@Valid @RequestBody DeployProjectReq req) throws IOException {
        deployService.deployProject(req);
    }
}
