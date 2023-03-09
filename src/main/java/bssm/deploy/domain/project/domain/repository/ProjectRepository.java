package bssm.deploy.domain.project.domain.repository;

import bssm.deploy.domain.project.domain.Project;
import bssm.deploy.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository <Project, Long> {

    boolean existsByDomainPrefix(String domainPrefix);

    Optional<Project> findByIdAndUser(long id, User user);
}
