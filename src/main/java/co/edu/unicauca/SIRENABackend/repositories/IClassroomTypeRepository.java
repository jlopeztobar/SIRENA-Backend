package co.edu.unicauca.SIRENABackend.repositories;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unicauca.SIRENABackend.common.enums.ClassroomTypeEnum;
import co.edu.unicauca.SIRENABackend.models.ClassroomTypeModel;
/**
 * Repositorio para acceder a la persistencia de las entidades ClassroomTypeModel.
 */
public interface IClassroomTypeRepository extends JpaRepository<ClassroomTypeModel, Integer> {

    ArrayList<ClassroomTypeModel> findAll();

    /**
     * Verifica si existe un tipo de aula con el nombre proporcionado.
     *
     * @param name Nombre del tipo de aula.
     * @return `true` si existe un tipo de aula con el nombre proporcionado, de lo contrario `false`.
     */
    boolean existsByName(ClassroomTypeEnum name);

    /**
     * Busca un tipo de aula por su identificador único.
     *
     * @param id Identificador único del tipo de aula.
     * @return Un objeto Optional que contiene el tipo de aula si se encuentra, o un Optional vacío si no se encuentra.
     */
    Optional<ClassroomTypeModel> findById(Integer id);
}
