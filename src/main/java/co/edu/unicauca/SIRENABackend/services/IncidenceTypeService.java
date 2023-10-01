package co.edu.unicauca.SIRENABackend.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.unicauca.SIRENABackend.models.IncidenceTypeModel;
import co.edu.unicauca.SIRENABackend.repositories.IIncidenceTypeRepository;

/**
 * Esta clase de servicio proporciona métodos para interactuar con entidades de tipo IncidenceTypeModel.
 */
@Service
public class IncidenceTypeService {
    @Autowired
    private IIncidenceTypeRepository incidenceTypeRepository;

    /**
     * Obtiene una lista de todas las entidades de tipo IncidenceTypeModel.
     *
     * @return ArrayList que contiene todas las entidades de tipo IncidenceTypeModel.
     */
    @Transactional(readOnly = true)
    public ArrayList<IncidenceTypeModel> getIncidenceTypes() {
        return (ArrayList<IncidenceTypeModel>) incidenceTypeRepository.findAll();
    }

    /**
     * Guarda una entidad de tipo IncidenceTypeModel.
     *
     * @param prmIncidence El IncidenceTypeModel que se va a guardar.
     * @return El IncidenceTypeModel guardado.
     */
    @Transactional
    public IncidenceTypeModel saveIncidenceTypes(IncidenceTypeModel prmIncidence) {
        return incidenceTypeRepository.save(prmIncidence);
    }

    /**
     * Obtiene una entidad de tipo IncidenceTypeModel por su ID.
     *
     * @param prmId El ID del IncidenceTypeModel que se desea recuperar.
     * @return Un Optional que contiene el IncidenceTypeModel si se encuentra, o un Optional vacío si no se encuentra.
     */
    @Transactional(readOnly = true)
    public Optional<IncidenceTypeModel> getIncidenceTypeById(Integer prmId) {
        return incidenceTypeRepository.findById(prmId);
    }

    /**
     * Elimina una entidad de tipo IncidenceTypeModel por su ID.
     *
     * @param prmId El ID del IncidenceTypeModel que se desea eliminar.
     * @return True si la eliminación es exitosa, false en caso contrario.
     */
    @Transactional
    public boolean deleteIncidenceTypeById(Integer prmId) {
        try {
            incidenceTypeRepository.deleteById(prmId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}