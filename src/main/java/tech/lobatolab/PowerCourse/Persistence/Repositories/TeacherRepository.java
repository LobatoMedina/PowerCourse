package tech.lobatolab.PowerCourse.Persistence.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import tech.lobatolab.PowerCourse.Persistence.Entities.TeacherEntity;

public interface TeacherRepository  extends JpaRepository<TeacherEntity, Long> {
    @Procedure(procedureName = "sp_create_teacher")
    void createTeacher(@Param("_email") String email, @Param("_password") String pswd, @Param("_name") String name, @Param("_specialty") Long specialtyId);

}
