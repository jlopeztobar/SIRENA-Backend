package co.edu.unicauca.SIRENABackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unicauca.SIRENABackend.models.RoleModel;

/**
 * Interfaz que define un repositorio para operaciones relacionadas con el rol del usuario.
 */
public interface IRoleRepository extends JpaRepository<RoleModel, Integer>{
}
