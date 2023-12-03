package co.edu.unicauca.SIRENABackend.services;

import java.util.ArrayList;
import java.util.Optional;

import co.edu.unicauca.SIRENABackend.dtos.request.IncidenceReq;
import co.edu.unicauca.SIRENABackend.dtos.response.IncidenceRes;

/**
 * Interfaz que define los servicios relacionados con las incidencias en el sistema.
 */
public interface IncidenceService {

    /**
     * Obtiene todas las incidencias en el sistema.
     *
     * @return Lista de todas las incidencias.
     */
    ArrayList<IncidenceRes> getIncidences();

    /**
     * Guarda una nueva incidencia en el sistema.
     *
     * @param prmIncidence La incidencia que se va a guardar.
     * @return La incidencia guardada.
     */
    IncidenceRes saveIncidence(IncidenceReq prmIncidence);

    /**
     * Obtiene una incidencia por su ID.
     *
     * @param prmId El ID de la incidencia que se va a obtener.
     * @return Un {@link Optional} que contiene la incidencia si se encuentra, o vacío si no.
     */
    Optional<IncidenceRes> getIncidenceById(Integer prmId);

    /**
     * Elimina una incidencia por su ID.
     *
     * @param prmId El ID de la incidencia que se va a eliminar.
     * @return `true` si la eliminación fue exitosa, `false` si no.
     */
    boolean deleteIncidenceById(Integer prmId);
}
