package tech.lobatolab.PowerCourse.Controllers.API;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.lobatolab.PowerCourse.Services.Interfaces.IImageService;

@RestController
@RequestMapping("/img")
@RequiredArgsConstructor
public class ImageController {

    @Autowired
    private final IImageService imageService;

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getImage(@PathVariable("id") Long id) {
        try {

            Resource imageResource = imageService.requestImageById(id).orElse(null);
            String xd =imageResource.getFilename().substring(imageResource.getFilename().lastIndexOf("."));
            return ResponseEntity.ok()
                    .contentType(MediaType.valueOf(xd))
                    .body(imageResource);


        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}