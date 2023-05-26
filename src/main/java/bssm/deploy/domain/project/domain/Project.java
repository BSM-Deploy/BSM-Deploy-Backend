package bssm.deploy.domain.project.domain;

import bssm.deploy.domain.project.domain.type.ProjectType;
import bssm.deploy.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String domainPrefix;

    @Column(nullable = false)
    private boolean isDeploy;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProjectType projectType;

    @Column(nullable = false)
    private long dataSize;

    @Column(columnDefinition = "text")
    private String envVar;

    public static Project create(User user, String name, String domainPrefix, ProjectType projectType) {
        Project project = new Project();
        project.user = user;
        project.name = name;
        project.domainPrefix = domainPrefix;
        project.isDeploy = false;
        project.projectType = projectType;
        project.dataSize = 0;
        return project;
    }

    public void setDeploy(boolean deploy) {
        isDeploy = deploy;
    }

    public void setDataSize(long dataSize) {
        this.dataSize = dataSize;
    }

    public void setEnvVar(String envVar) {
        this.envVar = envVar;
    }

    public boolean isContainerProject() {
        return ProjectType.BUILT_NEXT_JS == projectType
                || ProjectType.BUILT_SPRING_JAR == projectType;
    }
}
