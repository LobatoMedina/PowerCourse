package tech.lobatolab.PowerCourse.Persistence.DTOs;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SingUpDTO {
    private String password;
    private String email;
    private String name;
}
