package tech.lobatolab.PowerCourse.Persistence.DTOs;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.lobatolab.PowerCourse.Persistence.Entities.SpecialtyEntity;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class SpecialtyDto {
    private Long id;
    private String name;
    public SpecialtyDto(SpecialtyEntity sp){
        this.id = sp.getSpecialtyId();
        this.name = sp.getSpecialtySpecialty();

    }


}
