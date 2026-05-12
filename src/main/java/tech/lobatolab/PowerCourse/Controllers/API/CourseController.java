package tech.lobatolab.PowerCourse.Controllers.API;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tech.lobatolab.PowerCourse.Persistence.DTOs.UploadCourseDTO;
import tech.lobatolab.PowerCourse.Persistence.DTOs.UploadVideoDTO;
import tech.lobatolab.PowerCourse.Persistence.Entities.Views.CoursePreview;
import tech.lobatolab.PowerCourse.Services.Interfaces.ICourseService;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private final ICourseService courseService;


    @GetMapping("/view/all")
    public ResponseEntity<List<CoursePreview>> getAllCourse() {
        List<CoursePreview> courses = courseService.getallPreview();
        return ResponseEntity.ok(courses);
    }

    @PostMapping("/course/create")
    public ResponseEntity<Integer> createCourse(
            @RequestPart("uploadCourseDTO") UploadCourseDTO uploadCourseDTO,
            @RequestPart("thumbnail") MultipartFile thumbnail) {
        if(thumbnail.isEmpty()) {
            throw new RuntimeException("Thumbnail is empty try again");
        }
        if(uploadCourseDTO == null){
            throw new RuntimeException("Dto is empty");
        }
        try {
            Integer res = courseService.uploadCourse(uploadCourseDTO, thumbnail);
            return ResponseEntity.ok(res);
        } catch (IOException | RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(0);
        }
    }

    @PostMapping("/video/upload")
    public ResponseEntity<String> uploadVideo(
            @RequestPart("UploadVideoDTO") UploadVideoDTO uploadVideoDTO,
            @RequestParam("video") MultipartFile file) {
        try {
            courseService.uploadVideo(file, uploadVideoDTO);
            return ResponseEntity.ok("Video subido exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error al subir el video: " + e.getMessage());
        }
    }

    @PostMapping("/enroll/")
    public ResponseEntity<String> enrollCourse(
            @RequestParam Long idCourse,
            @RequestParam Long idCredential) {
        try {
            courseService.EnrollCourse(idCourse, idCredential);
            return ResponseEntity.ok("Inscripción realizada con éxito.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error en la inscripción.");
        }
    }

    @PostMapping("/course/score")
    public ResponseEntity<String> scoreCourse(
            @RequestParam Long credentialId,
            @RequestParam Long courseId,
            @RequestParam Integer punctuation) {
        try {
            courseService.scoreCourse(credentialId, courseId, punctuation);
            return ResponseEntity.ok("Calificación guardada exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al calificar el curso.");
        }
    }

    @GetMapping("/{idCourse}/certificate/{idUser}")
    public ResponseEntity<Resource> downloadCertificate(
            @PathVariable Long idCourse,
            @PathVariable Long idUser) {
        try {
            Resource pdfResource = courseService.printCertified(idCourse, idUser);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"certificado_" + idCourse + ".pdf\"")
                    .contentType(MediaType.APPLICATION_PDF)
                    .contentLength(pdfResource.contentLength())
                    .body(pdfResource);

        } catch (RuntimeException | IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}