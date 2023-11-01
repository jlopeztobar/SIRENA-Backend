package co.edu.unicauca.SIRENABackend.security.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unicauca.SIRENABackend.security.models.UserModel;

public interface IUserRepository extends JpaRepository<UserModel, Integer> {

    // @Query("SELECT id, username, firstName, lastName, email, role FROM
    // UserModel")
    // ArrayList<UserModel> findUserButNoPassword();

    Optional<UserModel> findByEmail(String prmEmail);

    Optional<UserModel> findByUsername(String prmUsername);

    boolean existsByUsername(String prmUsername);

    boolean existsByEmail(String prmEmail);
}
