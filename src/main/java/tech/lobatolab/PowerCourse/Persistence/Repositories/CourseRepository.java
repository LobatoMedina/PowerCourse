package tech.lobatolab.PowerCourse.Persistence.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import tech.lobatolab.PowerCourse.Persistence.Entities.CourseEntity;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {


    @Procedure(procedureName = "sp_create_course", outputParameterName = "_idCourse")
    Integer createCourse(@Param("_teacher") Long teacher_id,@Param("_title")  String title,@Param("_imgurl") String imgPath);
    @Procedure(procedureName = "sp_print_certified", outputParameterName = "response")
    Optional<Boolean> certifyIsAvaible(
            @Param("_idCourse") Long idCorse,
            @Param("_idCredential") Long credentialId
    );
    @Procedure(procedureName = "sp_enroll_course")
    void enrollCourse(
            @Param("_courseId") Long courseId,
            @Param("_credentialId") Long credentialId
    );
    @Procedure(procedureName = "sp_upload_video")
    void uploadVideo(
            @Param("_courseId") Long courseId,
            @Param("_url") String videoPath,
            @Param("_durationSeconds") Integer duration,
            @Param("_name") String name
    );
    @Procedure(procedureName = "sp_score_video" )
    void scoreCourse(
            @Param("_credentialId") Long credentialId,
            @Param("_courseId") Long courseId,
            @Param("_puntuation") Integer puntuation
    );


}
