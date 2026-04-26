package tech.lobatolab.PowerCourse.Persistence.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.lobatolab.PowerCourse.Persistence.Entities.VideoEntity;

public interface VideoRepository extends JpaRepository<VideoEntity, Long> {

}
