package co.edu.unicauca.SIRENABackend.services;

import java.util.ArrayList;
import java.util.Optional;

import co.edu.unicauca.SIRENABackend.dtos.request.IncidenceTypeReq;
import co.edu.unicauca.SIRENABackend.dtos.response.IncidenceTypeRes;

/**
 * Interfaz que define los servicios relacionados con los tipos de incidencias en el sistema.
 */
public interface IncidenceTypeService {

    /**
     * Obtiene todos los tipos de incidencias en el sistema.
     *
     * @return Lista de todos los tipos de incidencias.
     */
    ArrayList<IncidenceTypeRes> getIncidenceTypes();

    /**
     * Guarda un nuevo tipo de incidencia en el sistema.
     *
     * @param prmIncidence El tipo de incidencia que se va a guardar.
     * @return El tipo de incidencia guardado.
     */
    IncidenceTypeRes saveIncidenceTypes(IncidenceTypeReq prmIncidence);

    /**
     * Obtiene un tipo de incidencia por su ID.
     *
     * @param prmId El ID del tipo de incidencia que se va a obtener.
     * @return Un {@link Optional} que contiene el tipo de incidencia si se encuentra, o vacío si no.
     */
    Optional<IncidenceTypeRes> getIncidenceTypeById(Integer prmId);

    /**
     * Elimina un tipo de incidencia por su ID.
     *
     * @param prmId El ID del tipo de incidencia que se va a eliminar.
     * @return `true` si la eliminación fue exitosa, `false` si no.
     */
    boolean deleteIncidenceTypeById(Integer prmId);
}
