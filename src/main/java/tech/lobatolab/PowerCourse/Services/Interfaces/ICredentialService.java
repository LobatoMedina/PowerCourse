package tech.lobatolab.PowerCourse.Services.Interfaces;

import org.springframework.stereotype.Service;
import tech.lobatolab.PowerCourse.Persistence.DTOs.LoginDTO;
import tech.lobatolab.PowerCourse.Persistence.DTOs.SingUpDTO;
import tech.lobatolab.PowerCourse.Persistence.DTOs.TokenResponse;

@Service
public interface ICredentialService {
    TokenResponse logIn(LoginDTO loginDTO);
    TokenResponse SingUp(SingUpDTO singUp);
}
