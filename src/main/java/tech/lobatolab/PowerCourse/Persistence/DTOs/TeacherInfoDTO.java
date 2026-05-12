package tech.lobatolab.PowerCourse.Persistence.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor

public class TeacherInfoDTO {
    private Long id;
    private String name;
    private String email;
    private String specialty;

}
