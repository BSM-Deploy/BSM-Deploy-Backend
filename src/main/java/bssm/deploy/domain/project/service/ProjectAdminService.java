package bssm.deploy.domain.project.service;

import bssm.deploy.domain.project.domain.Project;
import bssm.deploy.domain.project.presentaion.dto.req.admin.FindProjectListAdminReq;
import bssm.deploy.domain.project.presentaion.dto.res.ProjectRes;
import bssm.deploy.global.dto.ListRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProjectAdminService {

    private final ProjectProvider projectProvider;

    public ProjectRes findProject(Long projectId) {
        Project project = projectProvider.findById(projectId);
        return ProjectRes.create(project);
    }

    public ListRes<ProjectRes> findProjectList(FindProjectListAdminReq req) {
        List<ProjectRes> projectResList = projectProvider.findProjectList(req.getUserId(), req.getProjectType(), req.isOrderRecent()).stream()
                .map(ProjectRes::create)
                .toList();
        return ListRes.create(projectResList);
    }

}
