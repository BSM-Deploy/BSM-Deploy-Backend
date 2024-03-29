package bssm.deploy.domain.project.domain.repository;

import bssm.deploy.domain.project.domain.Project;
import bssm.deploy.domain.project.domain.type.ProjectType;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static bssm.deploy.domain.project.domain.QProject.project;

@RequiredArgsConstructor
public class ProjectRepositoryImpl implements ProjectRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Project> findProjectList(Long userId, ProjectType projectType, boolean orderRecent) {
        return jpaQueryFactory.selectFrom(project)
                .where(
                        userIdEq(userId),
                        projectTypeEq(projectType)
                )
                .orderBy(
                        modifyDateOrder(orderRecent)
                )
                .fetch();
    }

    private BooleanExpression userIdEq(Long userId) {
        if (userId == null) {
            return null;
        }
        return project.user.id.eq(userId);
    }

    private BooleanExpression projectTypeEq(ProjectType projectType) {
        if (projectType == null) {
            return null;
        }
        return project.projectType.eq(projectType);
    }

    private OrderSpecifier<LocalDateTime> modifyDateOrder(boolean orderRecent) {
        if (orderRecent) {
            return project.modifiedAt.desc();
        }
        return project.modifiedAt.asc();
    }

}
