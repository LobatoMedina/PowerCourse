package tech.lobatolab.PowerCourse.Services.Interfaces;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public interface IImageService {
    Optional<Resource> requestImageById(Long id);
    Optional<Long> saveImageAndReturnId(MultipartFile multipartFile) throws IOException;
}
