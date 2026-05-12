package tech.lobatolab.PowerCourse.Persistence.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.lobatolab.PowerCourse.Persistence.Entities.SpecialtyEntity;

import java.util.Optional;

public interface SpecialtyRepository extends JpaRepository<SpecialtyEntity, Long > {

}
