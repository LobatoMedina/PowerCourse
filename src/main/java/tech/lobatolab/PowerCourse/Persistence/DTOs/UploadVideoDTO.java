package tech.lobatolab.PowerCourse.Persistence.DTOs;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UploadVideoDTO {
    private Long courseId;
    private Integer videoDuration;
    private  String name;

}
