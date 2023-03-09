package bssm.deploy.domain.project.service;

import bssm.deploy.domain.project.domain.Project;
import bssm.deploy.domain.project.domain.repository.ProjectRepository;
import bssm.deploy.domain.project.exception.AlreadyExistDomainPrefixException;
import bssm.deploy.domain.project.presentaion.dto.req.CreateProjectReq;
import bssm.deploy.global.auth.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProjectService {

    private final CurrentUser currentUser;

    private final ProjectRepository projectRepository;

    @Transactional
    public void createProject(CreateProjectReq req) {
        if (projectRepository.existsByDomainPrefix(req.getDomainPrefix())) {
            throw new AlreadyExistDomainPrefixException();
        }
        Project project = Project.create(currentUser.getUser(), req.getName(), req.getDomainPrefix(), req.getProjectType());
        projectRepository.save(project);
    }
}
