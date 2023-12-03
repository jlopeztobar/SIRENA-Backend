package co.edu.unicauca.SIRENABackend.security.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unicauca.SIRENABackend.security.models.UserModel;

public interface IUserRepository extends JpaRepository<UserModel, Integer> {

    Optional<UserModel> findByEmail(String prmEmail);

    Optional<UserModel> findByUsername(String prmUsername);

    Optional<UserModel> findById(Integer prmId);

    boolean existsByUsername(String prmUsername);

    boolean existsByEmail(String prmEmail);
}
