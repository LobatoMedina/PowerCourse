package tech.lobatolab.PowerCourse.Persistence.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import tech.lobatolab.PowerCourse.Persistence.Entities.VideoEntity;

import java.util.Optional;

public interface VideoRepository extends JpaRepository<VideoEntity, Long> {
    @Procedure(procedureName = "sp_is_video_watched", outputParameterName = "_isWatched")
    Boolean isVideoWatched(@Param("_videoId") Long video_id,
                           @Param("_credentialId") Long credentialId);

    @Procedure(procedureName = "sp_save_seconds_viewed", outputParameterName = "_isViewed")
    Optional<Boolean> saveSecondsViewed(@Param("_videoId") Long videoId ,
                               @Param("_userId") Long credentialId,
                               @Param("_secondsViewed") Integer secondsViewed);
    @Procedure(procedureName = "sp_get_next_video", outputParameterName = "_video")
    Optional<Long> getNextVideo(
            @Param("_courseId") Long courseId,
            @Param("_clientId") Long clientId
    );

}
