package tech.lobatolab.PowerCourse.Controllers.API;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.lobatolab.PowerCourse.Persistence.DTOs.LoginDTO;
import tech.lobatolab.PowerCourse.Persistence.DTOs.SingUpDTO;
import tech.lobatolab.PowerCourse.Persistence.DTOs.TokenResponse;
import tech.lobatolab.PowerCourse.Services.Interfaces.IAuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class Auth {


    private final IAuthService authService;

    @PostMapping("/logIn")
    public ResponseEntity<Integer> logIn(@RequestBody LoginDTO loginDTO) {
        try {

            Integer userId = authService.logIn(loginDTO);
            TokenResponse response = new TokenResponse(userId.toString(), "Login exitoso");

            return ResponseEntity.ok(3);

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(0);
        }
    }

    @PostMapping("/singUp")
    public ResponseEntity<TokenResponse> singUp(@RequestBody SingUpDTO singUpDTO) {
        try {
            authService.singUp(singUpDTO);
            TokenResponse response = new TokenResponse(null, "Registro completado con éxito. Por favor, inicia sesión.");

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }
}