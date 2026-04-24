package tech.lobatolab.PowerCourse.Controllers.API;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.lobatolab.PowerCourse.Persistence.DTOs.PreviewCourseDTO;
import tech.lobatolab.PowerCourse.Services.Interfaces.ICourseService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/courses")
public class CourseController {
    private final ICourseService courseService;

    @PostMapping("/course/create")
    private ResponseEntity<Boolean> createCourse(){
        return ResponseEntity.ok().body(false);
    }
    @GetMapping("course/view/all")
    private ResponseEntity<List<PreviewCourseDTO>> getAllCourse(){
        return ResponseEntity.ok().body(null);
    }
    @GetMapping("course/search={name}")
    private ResponseEntity<PreviewCourseDTO> getCourseByName(
            @PathParam("name") String name
    ){
        return  ResponseEntity.ok().body(null);
    }

}
