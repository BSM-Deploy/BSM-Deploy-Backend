package bssm.deploy.domain.project.constant;

import bssm.deploy.domain.project.domain.type.ProjectType;

import java.util.List;

public class ProjectFileConstant {

    public static final String HTML = "html";
    public static final String ZIP = "zip";
    public static final String JAR = "jar";

    public static final List<ProjectType> ZIP_FILE_PROJECTS = List.of(new ProjectType[]{
            ProjectType.MULTIPLE_FILE,
            ProjectType.BUILT_REACT_JS,
            ProjectType.BUILT_NEXT_JS,
            ProjectType.BUILT_NODE_JS
    });
}
