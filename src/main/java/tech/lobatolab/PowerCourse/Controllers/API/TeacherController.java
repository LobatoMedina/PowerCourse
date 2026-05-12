package tech.lobatolab.PowerCourse.Controllers.API;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.lobatolab.PowerCourse.Persistence.DTOs.SpecialtyDto;
import tech.lobatolab.PowerCourse.Persistence.DTOs.TeacherDTO;
import tech.lobatolab.PowerCourse.Persistence.DTOs.TeacherInfoDTO;
import tech.lobatolab.PowerCourse.Persistence.Entities.SpecialtyEntity;
import tech.lobatolab.PowerCourse.Services.Interfaces.ITeacherService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    private final ITeacherService teacherService;

    @PostMapping("/create")
    public ResponseEntity<String> createTeacher(@RequestBody TeacherDTO teacherDTO) {
        try {
            teacherService.createTeacher(teacherDTO);
            return ResponseEntity.ok("Profesor registrado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al registrar el profesor: " + e.getMessage());
        }
    }

    @GetMapping("/specialties")
    public ResponseEntity<List<SpecialtyDto>> getSpecialties() {
        try {
            List<SpecialtyDto> specialties = teacherService.getSpecialtys();
            return ResponseEntity.ok(specialties);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<TeacherInfoDTO>> getAllTeachers(){
        try{
            var teacher = teacherService.getAllTeachers();
            return ResponseEntity.ok().body(teacher);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(null);


        }

    }
}