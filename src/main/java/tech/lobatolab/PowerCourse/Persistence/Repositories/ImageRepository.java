package tech.lobatolab.PowerCourse.Persistence.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import tech.lobatolab.PowerCourse.Persistence.Entities.ImageEntity;

public interface ImageRepository extends JpaRepository<ImageEntity, Long> {

}
