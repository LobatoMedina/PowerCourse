package tech.lobatolab.PowerCourse.Services.Interfaces;


import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tech.lobatolab.PowerCourse.Persistence.DTOs.UploadCourseDTO;
import tech.lobatolab.PowerCourse.Persistence.DTOs.UploadVideoDTO;
import tech.lobatolab.PowerCourse.Persistence.Entities.Views.CoursePreview;

import java.io.IOException;
import java.util.List;

@Service
public interface ICourseService {
    List<CoursePreview> getallPreview ();
    Integer uploadCourse(UploadCourseDTO uploadCourseDTO, MultipartFile thumbnail) throws IOException;
    void EnrollCourse(Long idCourse, Long idUser);
    Resource printCertified(Long a, Long b);
    void scoreCourse(Long credentialId, Long courseId, Integer punctuation);
    void uploadVideo(MultipartFile file, UploadVideoDTO dto) throws IOException;


}
