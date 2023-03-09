package bssm.deploy.domain.project.domain.repository;

import bssm.deploy.domain.project.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository <Project, Long> {

    boolean existsByDomainPrefix(String domainPrefix);
}
