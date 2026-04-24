package tech.lobatolab.PowerCourse.Services.Implementations;



import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class VideoService {

    private final Path videoPath = Paths.get("courses/");
    public Resource returnVIdeoById(Long id){
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
}
