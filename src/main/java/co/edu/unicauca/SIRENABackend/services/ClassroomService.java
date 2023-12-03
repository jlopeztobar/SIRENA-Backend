package co.edu.unicauca.SIRENABackend.services;

import java.util.List;
import java.util.Optional;

import co.edu.unicauca.SIRENABackend.models.ClassroomModel;

/**
 * Interfaz que define los servicios relacionados con las aulas en el sistema.
 */
public interface ClassroomService {

    /**
     * Guarda una nueva aula en el sistema.
     *
     * @param classroom El aula que se va a guardar.
     * @return El aula guardada.
     */
    ClassroomModel save(ClassroomModel classroom);

    /**
     * Elimina un aula por su ID.
     *
     * @param id El ID del aula que se va a eliminar.
     */
    void delete(Integer id);

    /**
     * Obtiene un aula por su ID.
     *
     * @param id El ID del aula que se va a obtener.
     * @return Un {@link Optional} que contiene el aula si se encuentra, o vacío si no.
     */
    Optional<ClassroomModel> findById(Integer id);

    /**
     * Obtiene todas las aulas en el sistema.
     *
     * @return Lista de todas las aulas.
     */
    List<ClassroomModel> findAll();
}
