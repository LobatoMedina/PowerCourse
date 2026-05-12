package tech.lobatolab.PowerCourse.Persistence.Entities.Views;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="view_teacher_info")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TeacherView {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String specialty;


}
