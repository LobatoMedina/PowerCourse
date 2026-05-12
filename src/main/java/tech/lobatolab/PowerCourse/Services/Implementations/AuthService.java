package tech.lobatolab.PowerCourse.Services.Implementations;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.lobatolab.PowerCourse.Persistence.DTOs.LoginDTO;
import tech.lobatolab.PowerCourse.Persistence.DTOs.SingUpDTO;
import tech.lobatolab.PowerCourse.Persistence.Repositories.CredentialRepository;
import tech.lobatolab.PowerCourse.Services.Interfaces.IAuthService;


@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {
    private final CredentialRepository credentialRepository;

    public Integer logIn(LoginDTO dto){
        try{
            return credentialRepository.logIn(dto.getEmail(), dto.getPassword());
        }catch (Exception e){
            throw new RuntimeException("xd");
        }
    }
    public void singUp(SingUpDTO dto){
        credentialRepository.createUser(dto.getEmail(), dto.getPassword(), dto.getName());
    }

}
