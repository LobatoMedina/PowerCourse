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
@Table(name ="tbl_ope_course")
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long courseId;
    private Long teacherId;
    private int courseDurationSeconds;
    private int course_videos;
    private int imageId;

}
