package tech.lobatolab.PowerCourse.Persistence.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.lobatolab.PowerCourse.Persistence.Entities.Views.TeacherView;

public interface TeacherViewRepo extends JpaRepository<TeacherView, Long> {
}
