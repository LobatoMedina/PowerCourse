package tech.lobatolab.PowerCourse.Persistence.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import tech.lobatolab.PowerCourse.Persistence.Entities.CredentialEntity;

public interface CredentialRepository extends JpaRepository<CredentialEntity, Long> {
}
