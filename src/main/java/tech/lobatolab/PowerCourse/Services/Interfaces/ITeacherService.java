package tech.lobatolab.PowerCourse.Services.Interfaces;

import tech.lobatolab.PowerCourse.Persistence.DTOs.SpecialtyDto;
import tech.lobatolab.PowerCourse.Persistence.DTOs.TeacherDTO;
import tech.lobatolab.PowerCourse.Persistence.DTOs.TeacherInfoDTO;
import tech.lobatolab.PowerCourse.Persistence.Entities.SpecialtyEntity;
import tech.lobatolab.PowerCourse.Persistence.Repositories.SpecialtyRepository;

import java.util.List;

public interface ITeacherService {
    void createTeacher(TeacherDTO dto);
    List<SpecialtyDto> getSpecialtys();

    List<TeacherInfoDTO> getAllTeachers();
}
