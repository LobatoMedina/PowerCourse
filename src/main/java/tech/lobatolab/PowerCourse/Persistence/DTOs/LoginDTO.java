package tech.lobatolab.PowerCourse.Persistence.DTOs;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class LoginDTO {
    private String email;
    private String password;


}
