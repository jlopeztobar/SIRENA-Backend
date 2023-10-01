package co.edu.unicauca.SIRENABackend.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.unicauca.SIRENABackend.models.IncidenceModel;
import co.edu.unicauca.SIRENABackend.repositories.IIncidenceRepository;
/**
 * Esta clase de servicio proporciona métodos para interactuar con entidades de servicio de  Incidencia.
 */
@Service
public class IncidenceService {

    @Autowired
    private IIncidenceRepository incidenceRepository;

    /**
     * Obtiene una lista de todas las incidencias registradas.
     *
     * @return ArrayList que contiene todas las incidencias.
     */
    @Transactional(readOnly = true)
    public ArrayList<IncidenceModel> getIncidences() {
        return (ArrayList<IncidenceModel>) incidenceRepository.findAll();
    }

    /**
     * Guarda una nueva incidencia en la base de datos.
     *
     * @param prmIncidence La incidencia que se va a guardar.
     * @return La incidencia guardada.
     */
    @Transactional
    public IncidenceModel saveIncidence(IncidenceModel prmIncidence) {
        return incidenceRepository.save(prmIncidence);
    }

    /**
     * Obtiene una incidencia por su ID.
     *
     * @param prmId El ID de la incidencia que se desea recuperar.
     * @return Un Optional que contiene la incidencia si se encuentra, o un Optional vacío si no se encuentra.
     */
    @Transactional(readOnly = true)
    public Optional<IncidenceModel> getIncidenceById(Integer prmId) {
        return incidenceRepository.findById(prmId);
    }

    /**
     * Elimina una incidencia por su ID.
     *
     * @param prmId El ID de la incidencia que se desea eliminar.
     * @return True si la eliminación es exitosa, false en caso contrario.
     */
    @Transactional
    public boolean deleteIncidenceById(Integer prmId) {
        try {
            incidenceRepository.deleteById(prmId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
