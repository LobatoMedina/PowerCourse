package tech.lobatolab.PowerCourse.Persistence.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.lobatolab.PowerCourse.Persistence.Entities.CourseEntity;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
}
