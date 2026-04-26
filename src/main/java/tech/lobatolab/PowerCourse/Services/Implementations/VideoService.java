package tech.lobatolab.PowerCourse.Services.Implementations;



import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tech.lobatolab.PowerCourse.Services.Interfaces.IVideoService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class VideoService implements IVideoService {
    private static final List<String> ALLOWED_TYPES= Arrays.asList(".mp4");
    private final Path videoPath = Paths.get("courses/");
    @Override
    public Resource returnVideoById(Long id){
        try{
            Path file = videoPath.resolve("");
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
            throw new RuntimeException("Formato no aceptado");
        }
        if(!Files.exists(videoPath)){
            Files.createDirectories(videoPath);
        }
        //String extension = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        try{String filename = "VID" + LocalTime.now() +".mp4";
            Files.copy(multipartFile.getInputStream(), this.videoPath.resolve(filename));
            return filename;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
