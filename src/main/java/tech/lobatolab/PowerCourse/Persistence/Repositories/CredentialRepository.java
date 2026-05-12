package tech.lobatolab.PowerCourse.Persistence.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import tech.lobatolab.PowerCourse.Persistence.Entities.CredentialEntity;

import java.util.Optional;

public interface CredentialRepository extends JpaRepository<CredentialEntity, Long> {
    @Procedure(procedureName = "sp_create_user")
    void createUser(@Param("_email") String email, @Param("_password") String pswd, @Param("_name") String name);
    @Procedure(procedureName = "sp_log_in", outputParameterName = "_nivel")
    Integer logIn(
            @Param("_email") String email,
            @Param("_password") String password
    );

}
