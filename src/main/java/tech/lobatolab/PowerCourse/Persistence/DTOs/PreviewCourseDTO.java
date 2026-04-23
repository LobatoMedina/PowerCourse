package tech.lobatolab.PowerCourse.Persistence.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PreviewCourseDTO {
    private String name;
    private double rating;
    private String image_url;
    private String teacher_name;
}
