package bssm.deploy.domain.project.domain.repository;

import bssm.deploy.domain.project.domain.Project;

import java.util.List;

public interface ProjectRepositoryCustom {

    List<Project> findProjectList(Long userId, boolean orderRecent);
}
