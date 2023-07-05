package bssm.deploy.domain.project.domain.repository;

import bssm.deploy.domain.project.domain.Project;
import bssm.deploy.domain.project.domain.type.ProjectType;
import bssm.deploy.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository <Project, Long> {

    boolean existsByDomainPrefix(String domainPrefix);

    long countByUserAndProjectTypeIn(User user, List<ProjectType> projectTypeList);

    Optional<Project> findByIdAndUser(long id, User user);

    List<Project> findAllByUser(User user);
}
