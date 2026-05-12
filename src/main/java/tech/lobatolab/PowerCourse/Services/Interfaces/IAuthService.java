package tech.lobatolab.PowerCourse.Services.Interfaces;

import tech.lobatolab.PowerCourse.Persistence.DTOs.LoginDTO;
import tech.lobatolab.PowerCourse.Persistence.DTOs.SingUpDTO;

public interface IAuthService {
    Integer logIn(LoginDTO dto);
    void singUp(SingUpDTO dto);
}
