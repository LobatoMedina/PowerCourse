package tech.lobatolab.PowerCourse.Persistence.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
@Entity
@Table(name ="tbl_cat_specialty")
public class SpecialtyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long specialtyId;
    private String specialtySpecialty;

}
