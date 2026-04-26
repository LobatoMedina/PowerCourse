package tech.lobatolab.PowerCourse.Controllers.API;


import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tech.lobatolab.PowerCourse.Services.Interfaces.IVideoService;

import java.nio.file.Paths;

@RestController
@RequestMapping("/video")
@RequiredArgsConstructor
public class VideoController {
    private final IVideoService videoService;

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getVideo(
            @PathVariable("id") Long id){
        try{
            return  ResponseEntity.ok().body(videoService.returnVideoById(id));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(null);
        }

    }

}
