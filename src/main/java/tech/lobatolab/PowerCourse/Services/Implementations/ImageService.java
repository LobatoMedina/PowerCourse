package tech.lobatolab.PowerCourse.Services.Implementations;


import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tech.lobatolab.PowerCourse.Persistence.Repositories.ImageRepository;
import tech.lobatolab.PowerCourse.Services.Interfaces.IImageService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService implements IImageService {
    private static final List<String> ALLOWED_TYPES = Arrays.asList(".jpeg", ".jpg", ".web", ".png");
    private static final Path ImagePath = Path.of("Images/");
    private final ImageRepository imageRepository;

    @Override
    public Optional<Resource> requestImageById(Long id) {

        return Optional.empty();
    }

    @Override
    public Optional<Long> saveImageAndReturnId(MultipartFile multipartFile) throws IOException {
        var contentType =multipartFile.getContentType();
        if(contentType == null || ALLOWED_TYPES.contains(contentType)){
            throw new RuntimeException("Formato no permitido");
        }
        if(!Files.exists(ImagePath)){
            Files.createDirectories(ImagePath);
        }
        String extension = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        
        return Optional.empty();
    }
}
