package co.edu.unicauca.SIRENABackend.services.impl;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.unicauca.SIRENABackend.dtos.request.IncidenceTypeReq;
import co.edu.unicauca.SIRENABackend.dtos.response.IncidenceTypeRes;
import co.edu.unicauca.SIRENABackend.models.IncidenceTypeModel;
import co.edu.unicauca.SIRENABackend.repositories.IIncidenceTypeRepository;
import co.edu.unicauca.SIRENABackend.services.IncidenceTypeService;


/**
 * Implementación de {@link IncidenceTypeService} que interactúa con la capa de repositorio para realizar operaciones
 * relacionadas con los tipos de incidencias.
 */
@Service
public class IncidenceTypeServiceImpl implements IncidenceTypeService {
    @Autowired
    private IIncidenceTypeRepository incidenceTypeRepository;

    /**
     * Obtiene todos los tipos de incidencias en el sistema.
     *
     * @return Lista de tipos de incidencias.
     */
    @Transactional(readOnly = true)
    public ArrayList<IncidenceTypeRes> getIncidenceTypes() {

        ArrayList<IncidenceTypeModel> IncidenceTypes = incidenceTypeRepository.findAll();
        ArrayList<IncidenceTypeRes> IncidenceTypesRes = new ArrayList<>();

        for (IncidenceTypeModel incidenceType : IncidenceTypes) {
            IncidenceTypesRes.add(IncidenceTypeRes.builder().id(incidenceType.getId())
                    .name(incidenceType.getName()).build());
        }

        return IncidenceTypesRes;
    }

    /**
     * Guarda un nuevo tipo de incidencia en el sistema.
     *
     * @param prmIncidenceType El tipo de incidencia que se va a guardar.
     * @return El tipo de incidencia guardado.
     */

    @Transactional
    public IncidenceTypeRes saveIncidenceTypes(IncidenceTypeReq prmIncidenceType) {
        if (incidenceTypeRepository.existsByName(prmIncidenceType.getName())) {
            return null;
        }

        IncidenceTypeModel incidenceType = IncidenceTypeModel.builder()
                .name(prmIncidenceType.getName())
                .build();

        IncidenceTypeModel IncidenceTypeSave = incidenceTypeRepository.save(incidenceType);

        IncidenceTypeRes incidenceTypeRes = IncidenceTypeRes.builder()
                .id(IncidenceTypeSave.getId())
                .name(IncidenceTypeSave.getName())
                .build();

        return incidenceTypeRes;
    }

    /**
     * Obtiene un tipo de incidencia por su ID.
     *
     * @param prmId El ID del tipo de incidencia que se va a obtener.
     * @return Un {@link Optional} que contiene el tipo de incidencia si se encuentra, o vacío si no.
     */
    @Transactional(readOnly = true)
    public Optional<IncidenceTypeRes> getIncidenceTypeById(Integer prmId) {
        Optional<IncidenceTypeModel> incidenceTypeFound = incidenceTypeRepository.findById(prmId);

        IncidenceTypeRes incidenceTypeRes = incidenceTypeFound.map(incidenceType -> IncidenceTypeRes.builder()
                .id(incidenceType.getId())
                .name(incidenceType.getName())
                .build()).orElse(null);

        return incidenceTypeRes != null ? Optional.of(incidenceTypeRes) : Optional.empty();
    }

    /**
     * Elimina un tipo de incidencia por su ID.
     *
     * @param prmId El ID del tipo de incidencia que se va a eliminar.
     * @return true si se eliminó con éxito, false si no.
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