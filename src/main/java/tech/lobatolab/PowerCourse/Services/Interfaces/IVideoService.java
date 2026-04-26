package tech.lobatolab.PowerCourse.Services.Interfaces;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface IVideoService {
    Resource returnVideoById(Long id);
    String saveVideo(MultipartFile file) throws IOException;
    Boolean isVideoWatched(Long id);
    void saveSecondsViewed(int seconds);

}
