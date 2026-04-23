package tech.lobatolab.PowerCourse.Controllers.API;



import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import tech.lobatolab.PowerCourse.Persistence.DTOs.LoginDTO;
import tech.lobatolab.PowerCourse.Persistence.DTOs.SingUpDTO;
import tech.lobatolab.PowerCourse.Persistence.DTOs.TokenResponse;
import tech.lobatolab.PowerCourse.Services.Interfaces.ICredentialService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor

public class Auth {
    @Autowired
    private final ICredentialService credentialService;
    @PostMapping("/logIn")
    private ResponseEntity<TokenResponse> logIn(
            @RequestPart LoginDTO loginDTO

            ){

        return ResponseEntity.ok().body(null);
    }
    @PostMapping("/singUp")
    private ResponseEntity<TokenResponse> SingUp(
            @RequestPart SingUpDTO singUp
            ){
        return ResponseEntity.ok().body(null);
    }
}
