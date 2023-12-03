package co.edu.unicauca.SIRENABackend.services;

import java.util.List;
import java.util.Optional;

import co.edu.unicauca.SIRENABackend.models.FacultyModel;

/**
 * Interfaz que define los servicios relacionados con las facultades en el sistema.
 */
public interface FacultyService {

    /**
     * Obtiene todas las facultades en el sistema.
     *
     * @return Lista de todas las facultades.
     */
    List<FacultyModel> getFaculties();

    /**
     * Guarda una nueva facultad en el sistema.
     *
     * @param prmIncidence La facultad que se va a guardar.
     * @return La facultad guardada.
     */
    FacultyModel saveFaculties(FacultyModel prmIncidence);

    /**
     * Obtiene una facultad por su ID.
     *
     * @param prmId El ID de la facultad que se va a obtener.
     * @return Un {@link Optional} que contiene la facultad si se encuentra, o vacío si no.
     */
    Optional<FacultyModel> getFacultyById(Integer prmId);

    /**
     * Elimina una facultad por su ID.
     *
     * @param prmId El ID de la facultad que se va a eliminar.
     * @return `true` si la eliminación fue exitosa, `false` si no.
     */
    boolean deleteFacultyById(Integer prmId);
}
