package bssm.deploy.domain.project.service;

import bssm.deploy.domain.project.domain.Project;
import bssm.deploy.domain.project.domain.repository.ProjectRepository;
import bssm.deploy.domain.project.domain.type.ProjectType;
import bssm.deploy.domain.project.exception.NoSuchProjectException;
import bssm.deploy.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProjectProvider {

    private final ProjectRepository projectRepository;

    public Project findById(long id) {
        return projectRepository.findById(id)
                .orElseThrow(NoSuchProjectException::new);
    }

    public Project findByIdAndUser(long id, User user) {
        return projectRepository.findByIdAndUser(id, user)
                .orElseThrow(NoSuchProjectException::new);
    }

    public List<Project> findProjectList(User user) {
        return projectRepository.findAllByUser(user);
    }

    public List<Project> findProjectList(Long userId, ProjectType projectType, boolean orderRecent) {
        return projectRepository.findProjectList(userId, projectType, orderRecent);
    }
}
