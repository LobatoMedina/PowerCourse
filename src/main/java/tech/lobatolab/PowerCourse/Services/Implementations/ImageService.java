package tech.lobatolab.PowerCourse.Services.Implementations;


import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriBuilder;
import tech.lobatolab.PowerCourse.Persistence.Entities.ImageEntity;
import tech.lobatolab.PowerCourse.Persistence.Repositories.ImageRepository;
import tech.lobatolab.PowerCourse.Services.Interfaces.IImageService;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
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
    public Optional<Resource> requestImageById(Long idImage) {
        var image = imageRepository.findById(idImage).orElseGet(null).getImageUrl();
        if (image==null)
        return Optional.empty();
        else{
            Path path = ImagePath.resolve(image);
            try {
                Resource resource = new UrlResource(path.toUri());
                return Optional.of(resource);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Optional<String> saveImageAndReturnPath(MultipartFile multipartFile) throws IOException {
        var contentType =multipartFile.getContentType();
        if(contentType == null || ALLOWED_TYPES.contains(contentType)){
            throw new RuntimeException("Formato no permitido");
        }
        if(!Files.exists(ImagePath)){
            Files.createDirectories(ImagePath);
            return Optional.empty();
        }
        String extension = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        String filename = "image_"+LocalDate.now() + extension;
        Files.copy(multipartFile.getInputStream(), ImagePath.resolve(filename));
        return Optional.of(filename);
    }
}
