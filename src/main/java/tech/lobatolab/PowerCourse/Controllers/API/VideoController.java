package tech.lobatolab.PowerCourse.Controllers.API;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.lobatolab.PowerCourse.Services.Interfaces.IVideoService;

@RestController
@RequestMapping("/video")
@RequiredArgsConstructor
public class VideoController {

    private final IVideoService videoService;

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getVideo(@PathVariable("id") Long id) {
        try {
            Resource videoResource = videoService.returnVideoById(id);

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("video/mp4"))
                    .header(HttpHeaders.ACCEPT_RANGES, "bytes")
                    .body(videoResource);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(null);
        }
    }
    @GetMapping("/save/{id}/{idCredential}=?{seconds}")
    public ResponseEntity<String> saveViewSeconds(
            @PathVariable("id") Long id,
            @PathVariable("idCredential") Long idCredential,
            @PathVariable("seconds") Integer seconds
    ){
        try{
            videoService.saveSecondsViewed(id,idCredential,seconds);
            return ResponseEntity.ok().body("Success");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("error :"+e.getMessage());
        }
    }
    @GetMapping("/{idCourse}/{idCredential}")
    public ResponseEntity<Long> getNextVideo(
            @Param("idCourse") Long idCourse,
            @Param("idCredential") Long idCredential
    ){
        try{
            return ResponseEntity.ok().body(videoService.getNextVideo(idCourse,idCredential ));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(0L);
        }
    }


}