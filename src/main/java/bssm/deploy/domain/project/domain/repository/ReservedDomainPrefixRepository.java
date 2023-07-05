package bssm.deploy.domain.project.domain.repository;

import bssm.deploy.domain.project.domain.ReservedDomainPrefix;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservedDomainPrefixRepository extends JpaRepository <ReservedDomainPrefix, String> {

    boolean existsByDomainPrefix(String domainPrefix);
}
