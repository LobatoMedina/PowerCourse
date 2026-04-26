package tech.lobatolab.PowerCourse.Services.Implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tech.lobatolab.PowerCourse.Persistence.DTOs.PreviewCourseDTO;
import tech.lobatolab.PowerCourse.Persistence.DTOs.UploadCourseDTO;
import tech.lobatolab.PowerCourse.Services.Interfaces.ICourseService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService implements ICourseService {

    @Override
    public List<PreviewCourseDTO> getallPreview() {
        return List.of();
    }

    @Override
    public void uploadCourse(UploadCourseDTO uploadCourseDTO, MultipartFile thumbnail) {

    }

    @Override
    public void archiveCoruse(Long idCourse) {

    }

    @Override
    public void EnrollCourse(Long idCourse, Long idUser) {

    }

    @Override
    public void findCourseByName(String name) {

    }

    @Override
    public MultipartFile printCertified() {
        return null;
    }
}
