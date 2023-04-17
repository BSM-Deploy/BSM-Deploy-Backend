package bssm.deploy.domain.container.controller;

import bssm.deploy.domain.container.service.ContainerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Tag(name = "컨테이너")
@RestController
@RequestMapping("container")
@RequiredArgsConstructor
public class ContainerController {

    private final ContainerService containerLogService;

    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "컨테이너를 찾을 수 없음", content = @Content),
            @ApiResponse(responseCode = "500", content = @Content)
    })
    @Operation(summary = "컨테이너 로그 조회")
    @GetMapping("{projectId}/log")
    public String deployProject(@PathVariable Long projectId) throws IOException {
        return containerLogService.getContainerLog(projectId);
    }

}
