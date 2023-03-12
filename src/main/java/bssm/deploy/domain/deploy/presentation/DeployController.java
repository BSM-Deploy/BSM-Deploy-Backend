package bssm.deploy.domain.deploy.presentation;

import bssm.deploy.domain.deploy.presentation.dto.req.CancelDeployProjectReq;
import bssm.deploy.domain.deploy.presentation.dto.req.DeployProjectReq;
import bssm.deploy.domain.deploy.service.CancelDeployService;
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
    private final CancelDeployService cancelDeployService;

    @Operation(summary = "배포 하기")
    @PutMapping
    public void deployProject(@Valid @RequestBody DeployProjectReq req) throws IOException {
        deployService.deployProject(req);
    }

    @Operation(summary = "배포 취소하기")
    @PutMapping("cancel")
    public void cancelDeployProject(@Valid @RequestBody CancelDeployProjectReq req) throws IOException {
        cancelDeployService.cancelDeployProject(req);
    }
}
