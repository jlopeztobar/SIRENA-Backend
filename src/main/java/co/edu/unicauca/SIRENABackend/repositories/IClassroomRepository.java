package co.edu.unicauca.SIRENABackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unicauca.SIRENABackend.models.ClassroomModel;

/**
 * Repositorio para acceder a la persistencia de las entidades BuildingModel.
 */
public interface IClassroomRepository extends JpaRepository<ClassroomModel, Integer> {

}
