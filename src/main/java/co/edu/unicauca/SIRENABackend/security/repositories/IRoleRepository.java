package co.edu.unicauca.SIRENABackend.security.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unicauca.SIRENABackend.security.common.enums.RoleEnum;
import co.edu.unicauca.SIRENABackend.security.models.RoleModel;

public interface IRoleRepository extends JpaRepository<RoleModel, Integer> {
    Optional<RoleModel> findByName(RoleEnum prmRoleName);

    boolean existsByName(String prmRoleName);

}
