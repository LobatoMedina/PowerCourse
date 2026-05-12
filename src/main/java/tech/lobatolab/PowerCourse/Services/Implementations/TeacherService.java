package tech.lobatolab.PowerCourse.Services.Implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.lobatolab.PowerCourse.Persistence.DTOs.SpecialtyDto;
import tech.lobatolab.PowerCourse.Persistence.DTOs.TeacherDTO;
import tech.lobatolab.PowerCourse.Persistence.DTOs.TeacherInfoDTO;
import tech.lobatolab.PowerCourse.Persistence.Entities.SpecialtyEntity;
import tech.lobatolab.PowerCourse.Persistence.Repositories.SpecialtyRepository;
import tech.lobatolab.PowerCourse.Persistence.Repositories.TeacherRepository;
import tech.lobatolab.PowerCourse.Persistence.Repositories.TeacherViewRepo;
import tech.lobatolab.PowerCourse.Services.Interfaces.ITeacherService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TeacherService implements ITeacherService {
    private final TeacherRepository teacherRepository;
    private final SpecialtyRepository specialtyRepository;
    private final TeacherViewRepo teacherViewRepository;
    @Override
    public void createTeacher(TeacherDTO dto) {
        teacherRepository.createTeacher(dto.getEmail(), dto.getPassword(), dto.getName(), dto.getSpecialtyId());
    }

    @Override
    public List<SpecialtyDto> getSpecialtys() {

        return specialtyRepository.findAll().stream().map(SpecialtyDto::new).collect(Collectors.toList());
    }

    @Override
    public List<TeacherInfoDTO> getAllTeachers() {
        return teacherViewRepository.findAll().stream().map(teacherView -> {
            return TeacherInfoDTO.builder().id(teacherView.getId())
                    .name(teacherView.getName())
                    .email(teacherView.getEmail())
                    .specialty(teacherView.getSpecialty())
                    .build();
        }).collect(Collectors.toList());
    }
}
