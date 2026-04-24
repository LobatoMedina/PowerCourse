package tech.lobatolab.PowerCourse.Controllers.API;


import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Paths;

@RestController
@RequestMapping("/video")
@RequiredArgsConstructor
public class VideoController {


    @GetMapping("/{id}")
    public ResponseEntity<Resource> geetVideo(
            @PathVariable("id") Long id){
        return ResponseEntity.ok().body(null);
    }

}
