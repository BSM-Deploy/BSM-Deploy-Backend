package bssm.deploy.global.utils;

import bssm.deploy.domain.project.domain.type.ProjectType;
import lombok.experimental.UtilityClass;

import static bssm.deploy.domain.project.constant.ProjectConstant.CONTAINER_PROJECTS;

@UtilityClass
public class ProjectUtil {

    public boolean isContainerProject(ProjectType projectType) {
        return CONTAINER_PROJECTS.contains(projectType);
    }
}
