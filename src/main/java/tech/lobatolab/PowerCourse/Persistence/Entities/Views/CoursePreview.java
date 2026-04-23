package tech.lobatolab.PowerCourse.Persistence.Entities.Views;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.View;

@Entity
@Table(name ="view_preview_course")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CoursePreview {
}
