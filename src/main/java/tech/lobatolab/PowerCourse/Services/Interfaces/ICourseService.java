package tech.lobatolab.PowerCourse.Services.Interfaces;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tech.lobatolab.PowerCourse.Persistence.DTOs.LoginDTO;
import tech.lobatolab.PowerCourse.Persistence.DTOs.PreviewCourseDTO;
import tech.lobatolab.PowerCourse.Persistence.DTOs.SingUpDTO;
import tech.lobatolab.PowerCourse.Persistence.DTOs.UploadCourseDTO;

import java.util.List;

@Service
public interface ICourseService {
    List<PreviewCourseDTO> getallPreview ();
    void uploadCourse(UploadCourseDTO uploadCourseDTO, MultipartFile thumbnail);
    void archiveCoruse(Long idCourse );
    void EnrollCourse(Long idCourse, Long idUser);
    void findCourseByName(String name);
    MultipartFile printCertified();



}
