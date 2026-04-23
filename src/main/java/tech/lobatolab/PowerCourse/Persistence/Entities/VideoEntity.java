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
@Table(name ="tbl_ope_video")
public class VideoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long videoId;
    private String videoUrl;
    private int videoDurationSeconds;
}
