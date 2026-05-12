package tech.lobatolab.PowerCourse.Persistence.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tbl_rel_video_credential")
public class VideoCredential {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long videoCredentialId;
    private Long videoId;
    private Long credentialId;
    private Date dateLastSeen;
    private int secondsViewed;
    private Boolean videoIsWatched;



}
