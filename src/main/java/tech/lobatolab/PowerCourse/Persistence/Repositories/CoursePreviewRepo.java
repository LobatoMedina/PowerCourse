package tech.lobatolab.PowerCourse.Persistence.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.lobatolab.PowerCourse.Persistence.Entities.Views.CoursePreview;

public interface CoursePreviewRepo extends JpaRepository<CoursePreview, Long> {
}
