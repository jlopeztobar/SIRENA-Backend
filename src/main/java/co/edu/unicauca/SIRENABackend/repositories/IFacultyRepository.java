package co.edu.unicauca.SIRENABackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unicauca.SIRENABackend.models.FacultyModel;

/**
 * Repositorio para acceder a la persistencia de las entidades FacultyModel.
 */
public interface IFacultyRepository extends JpaRepository<FacultyModel, Integer> {
}
