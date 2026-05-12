package tech.lobatolab.PowerCourse.Services.Implementations;



import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tech.lobatolab.PowerCourse.Persistence.Repositories.VideoRepository;
import tech.lobatolab.PowerCourse.Services.Interfaces.IVideoService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VideoService implements IVideoService {
    private static final List<String> ALLOWED_TYPES= Arrays.asList("video/mp4");
    private final Path videoPath = Paths.get("Uploads/courses");
    private final VideoRepository videoRepository;
    @Override
    public Resource returnVideoById(Long id){
        var videoUlr = videoRepository.findById(id).orElseThrow(() -> {throw  new RuntimeException("");}).getVideoUrl();
        try{
            Path file = videoPath.resolve(videoUlr);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }else {
                throw new RuntimeException("No se pudo leer el archivo o no existe.");
            }
        }catch (IOException e){
            throw new RuntimeException("No se pudo encontrar el archivo: " + e.getMessage());
        }
    }
    public String saveVideo(MultipartFile multipartFile) throws IOException {
        String contentype = multipartFile.getContentType();

        if(contentype == null || !ALLOWED_TYPES.contains(contentype)){
            throw new RuntimeException("Formato no aceptado"+ contentype);
        }
        if(!Files.exists(videoPath)){
            Files.createDirectories(videoPath);
        }
        try{
            String filename = "VID_" + LocalTime.now()+ UUID.randomUUID() +".mp4";
            Files.copy(multipartFile.getInputStream(), this.videoPath.resolve(filename));
            return filename;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean isVideoWatched(Long id,Long credentialId) {
        return  videoRepository.isVideoWatched(id, credentialId);

    }
    @Override
    public void saveSecondsViewed(Long videoId,Long credentialId, Integer secondsViewed) {
        videoRepository.saveSecondsViewed(videoId, credentialId, secondsViewed).orElseThrow(() -> {throw new RuntimeException("Video no encontrado");});
    }
    @Override
    public Long getNextVideo(Long courseId, Long credentialId){
        return  videoRepository.getNextVideo(courseId,credentialId).orElseThrow(()->{
            throw new RuntimeException("Sexo");
        });

    }

}
