package tech.lobatolab.PowerCourse.Persistence.DTOs;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UploadCourseDTO {
    private String name;
    private Long teacher_id;
    private String urlImage;
}
