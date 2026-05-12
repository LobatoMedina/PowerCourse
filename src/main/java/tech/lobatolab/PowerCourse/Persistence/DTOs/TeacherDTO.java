package tech.lobatolab.PowerCourse.Persistence.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor

public class TeacherDTO {
    private String password;
    private String email;
    private String name;
    private Long specialtyId;



}
