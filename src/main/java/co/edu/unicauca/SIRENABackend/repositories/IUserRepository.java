package co.edu.unicauca.SIRENABackend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unicauca.SIRENABackend.models.UserModel;

/**
 * Interfaz que define un repositorio para operaciones relacionadas con UserModel.
 */
public interface IUserRepository extends JpaRepository<UserModel, Integer>{

    /**
     * Busca un usuario por su dirección de correo electrónico.
     *
     * @param email La dirección de correo electrónico del usuario que se busca.
     * @return Un objeto Optional que contiene el usuario si se encuentra, o vacío si no se encuentra.
     */
    Optional<UserModel> findByEmail(String prmEmail);
    Optional<UserModel> findByUsername(String prmUsername);
}
