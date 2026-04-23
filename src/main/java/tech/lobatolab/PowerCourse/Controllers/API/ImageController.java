package tech.lobatolab.PowerCourse.Controllers.API;


import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tech.lobatolab.PowerCourse.Services.Interfaces.IImageService;

@RestController
@RequestMapping("/img")
@RequiredArgsConstructor
public class ImageController {
    private final IImageService imageService;
    @GetMapping("/img/url={id}")
    private ResponseEntity<MultipartFile> getImage(
            @PathParam("id") Long id
    ){
        return ResponseEntity.ok().body(null);
    }
}
