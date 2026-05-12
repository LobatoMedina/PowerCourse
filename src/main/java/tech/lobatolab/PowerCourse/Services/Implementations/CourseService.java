package tech.lobatolab.PowerCourse.Services.Implementations;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tech.lobatolab.PowerCourse.Persistence.DTOs.UploadCourseDTO;
import tech.lobatolab.PowerCourse.Persistence.DTOs.UploadVideoDTO;
import tech.lobatolab.PowerCourse.Persistence.Entities.Views.CoursePreview;
import tech.lobatolab.PowerCourse.Persistence.Repositories.CoursePreviewRepo;
import tech.lobatolab.PowerCourse.Persistence.Repositories.CourseRepository;
import tech.lobatolab.PowerCourse.Persistence.Repositories.CredentialRepository;
import tech.lobatolab.PowerCourse.Services.Interfaces.ICourseService;
import tech.lobatolab.PowerCourse.Services.Interfaces.IImageService;
import tech.lobatolab.PowerCourse.Services.Interfaces.IVideoService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService implements ICourseService {
    @Autowired
    private final CourseRepository courseRepository;
    @Autowired
    private final CredentialRepository credentialRepository;
    @Autowired
    private final CoursePreviewRepo coursePreviewRepo;
    @Autowired
    private final IImageService imageService;
    @Autowired
    private final IVideoService videoService;
    @Override
    public List<CoursePreview> getallPreview() {
        return coursePreviewRepo.findAll().stream().toList();
    }

    @Override
    public Integer uploadCourse(UploadCourseDTO uploadCourseDTO, MultipartFile thumbnail) throws IOException {
        return courseRepository.createCourse(uploadCourseDTO.getTeacher_id(),uploadCourseDTO.getName(), imageService.saveImageAndReturnPath(thumbnail).orElseThrow(
                ()->{throw new RuntimeException("Error al subir la imagen");}
        ));
    }

    @Override
    public void EnrollCourse(Long idCourse, Long idUser) {
        courseRepository.enrollCourse(idCourse,idUser);
    }
    @Override
    public void scoreCourse(Long credentialId, Long courseId, Integer punctuation){
        courseRepository.scoreCourse(credentialId,courseId, punctuation);
    }
    @Override
    public void uploadVideo(MultipartFile file, UploadVideoDTO dto) throws IOException {
        try{
            courseRepository.uploadVideo(dto.getCourseId(), videoService.saveVideo(file), dto.getVideoDuration(),dto.getName());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public Resource printCertified(Long idCorse, Long credentialId) {
        var xd =courseRepository.certifyIsAvaible(idCorse, credentialId).orElseThrow(()->{
            throw new RuntimeException("id no encontrado");
        }).booleanValue();
        if(!xd){
            throw new RuntimeException("No haz terminado el curso");
        }
        String studentName = credentialRepository.findById(idCorse).orElseThrow(() ->{throw new RuntimeException("nombre no encontrado");}).getCredentialName();
        String courseName = courseRepository.findById(idCorse).orElseThrow(() -> {throw new RuntimeException("Curso no encontrado ");}).getCourseCourse();

        String instructorName = coursePreviewRepo.findById(idCorse).orElseThrow(()-> {throw new RuntimeException("Profe no encontrado");}).getTeacherName();
        String platformName = "PowerCourse Platform";

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {

            PdfWriter writer = new PdfWriter(outputStream);
            PdfDocument pdf = new PdfDocument(writer);

            Document document = new Document(pdf, PageSize.LETTER.rotate());


            document.setMargins(40, 40, 40, 40);


            DeviceRgb primaryColor = new DeviceRgb(44, 62, 80);   // Azul oscuro
            DeviceRgb accentColor = new DeviceRgb(231, 76, 60);   // Rojo
            DeviceRgb goldColor = new DeviceRgb(212, 175, 55);    // Dorado


            Div borderDiv = new Div()
                    .setBorder(new SolidBorder(primaryColor, 5))
                    .setPadding(30)
                    .setHeight(500)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE);


            Paragraph header = new Paragraph(platformName.toUpperCase())
                    .setFontColor(primaryColor)
                    .setFontSize(24)
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginBottom(30);
            Paragraph subtitle = new Paragraph("CERTIFICADO DE FINALIZACIÓN")
                    .setFontColor(goldColor)
                    .setFontSize(32)
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginBottom(10);

            Paragraph text1 = new Paragraph("Se otorga el presente reconocimiento a:")
                    .setFontSize(14)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginBottom(20);

            Paragraph name = new Paragraph(studentName)
                    .setFontColor(primaryColor)
                    .setFontSize(40)
                    .setBold()
                    .setItalic()
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginBottom(20);

            Paragraph text2 = new Paragraph("Por haber completado con éxito el curso:")
                    .setFontSize(14)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginBottom(10);

            Paragraph course = new Paragraph(courseName)
                    .setFontColor(accentColor)
                    .setFontSize(22)
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginBottom(40);

            Paragraph footer = new Paragraph()
                    .add("Impartido por: " + instructorName + "\n")
                    .setFontSize(12)
                    .setFontColor(ColorConstants.DARK_GRAY)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginTop(30);

            borderDiv.add(header);
            borderDiv.add(subtitle);
            borderDiv.add(text1);
            borderDiv.add(name);
            borderDiv.add(text2);
            borderDiv.add(course);
            borderDiv.add(footer);

            document.add(borderDiv);
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  new ByteArrayResource(outputStream.toByteArray());
    }

}
