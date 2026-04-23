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
@Table(name ="tbl_ope_credential")
public class CredentialEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long credentialId;
    private String credentialEmail;
    private String credentialPassword;
    private String credentialName;
    private Long rol_id;
}
