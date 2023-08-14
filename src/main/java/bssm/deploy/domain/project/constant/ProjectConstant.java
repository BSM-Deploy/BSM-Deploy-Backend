package bssm.deploy.domain.project.constant;

import bssm.deploy.domain.project.domain.type.ProjectType;

import java.util.List;

public class ProjectConstant {

    public static final List<ProjectType> CONTAINER_PROJECTS = List.of(new ProjectType[]{
            ProjectType.BUILT_NODE_JS,
            ProjectType.BUILT_NEXT_JS,
            ProjectType.BUILT_SPRING_JAR
    });
}
