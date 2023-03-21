package bssm.deploy.domain.container.controller;

import bssm.deploy.domain.container.service.ContainerLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Tag(name = "컨테이너")
@RestController
@RequestMapping("container")
@RequiredArgsConstructor
public class ContainerController {

    private final ContainerLogService containerLogService;

    @Operation(summary = "컨테이너 로그 조회")
    @GetMapping("{projectId}/log")
    public String deployProject(@PathVariable Long projectId) throws IOException, InterruptedException {
        return containerLogService.getContainerLog(projectId);
    }

}
