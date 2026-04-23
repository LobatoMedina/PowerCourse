package tech.lobatolab.PowerCourse.Services.Interfaces;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface IImageService {
    MultipartFile requestImageById(Long id);
}
