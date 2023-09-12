package bssm.deploy.domain.project.domain.repository;

import bssm.deploy.domain.project.domain.Project;
import bssm.deploy.domain.project.domain.type.ProjectType;

import java.util.List;

public interface ProjectRepositoryCustom {

    List<Project> findProjectList(Long userId, ProjectType projectType, boolean orderRecent);
}
