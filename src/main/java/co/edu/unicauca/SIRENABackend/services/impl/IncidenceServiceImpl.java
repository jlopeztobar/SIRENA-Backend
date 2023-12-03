package co.edu.unicauca.SIRENABackend.services.impl;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.unicauca.SIRENABackend.dtos.request.IncidenceReq;
import co.edu.unicauca.SIRENABackend.dtos.response.IncidenceRes;
import co.edu.unicauca.SIRENABackend.models.IncidenceModel;
import co.edu.unicauca.SIRENABackend.models.IncidenceTypeModel;
import co.edu.unicauca.SIRENABackend.repositories.IIncidenceRepository;
import co.edu.unicauca.SIRENABackend.repositories.IIncidenceTypeRepository;
import co.edu.unicauca.SIRENABackend.security.models.UserModel;
import co.edu.unicauca.SIRENABackend.security.repositories.IUserRepository;
import co.edu.unicauca.SIRENABackend.services.IncidenceService;

/**
 * Implementación de {@link IncidenceService} que interactúa con la capa de repositorio para realizar operaciones
 * relacionadas con las incidencias.
 */
@Service
public class IncidenceServiceImpl implements IncidenceService {

    @Autowired
    private IIncidenceRepository incidenceRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IIncidenceTypeRepository incidenceTypeRepository;

    /**
     * Obtiene todas las incidencias en el sistema.
     *
     * @return Lista de incidencias.
     */
    @Override
    @Transactional(readOnly = true)
    public ArrayList<IncidenceRes> getIncidences() {

        ArrayList<IncidenceModel> incidencesFound = (ArrayList<IncidenceModel>) incidenceRepository.findAll();
        ArrayList<IncidenceRes> incidencesRes = new ArrayList<>();

        for (IncidenceModel incidence : incidencesFound) {
            incidencesRes.add(IncidenceRes.builder()
                    .id(incidence.getId())
                    .name(incidence.getName())
                    .teacherName(incidence.getTeacherName().getUsername())
                    .incidenceType(incidence.getInsidenciaType())
                    .build());
        }

        return incidencesRes;
    }

    /**
     * Guarda una nueva incidencia en el sistema.
     *
     * @param prmIncidence La incidencia que se va a guardar.
     * @return La incidencia guardada.
     */
    @Override
    @Transactional
    public IncidenceRes saveIncidence(IncidenceReq prmIncidence) {
        if(prmIncidence.getName().isEmpty())
            return null;
        IncidenceTypeModel incidenceFound = incidenceTypeRepository
                .findByName(prmIncidence.getIncidenceType());
        if (incidenceFound == null) {
            return null;
        }
        Optional<UserModel> teacherFound = userRepository.findByUsername(prmIncidence.getTeacherName());
        if (!teacherFound.isPresent()) {
            return null;
        }

        IncidenceModel incidence = IncidenceModel.builder()
                .name(prmIncidence.getName())
                .teacherName(teacherFound.get())
                .insidenciaType(incidenceFound)
                .build();
        IncidenceModel incidenceSave = incidenceRepository.save(incidence);

        IncidenceRes incidenceRes = IncidenceRes.builder()
                .id(incidenceSave.getId())
                .name(incidenceSave.getName())
                .teacherName(incidenceSave.getTeacherName().getUsername())
                .incidenceType(incidenceSave.getInsidenciaType())
                .build();

        return incidenceRes;
    }

    /**
     * Obtiene una incidencia por su ID.
     *
     * @param prmId El ID de la incidencia que se va a obtener.
     * @return Un {@link Optional} que contiene la incidencia si se encuentra, o vacío si no.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<IncidenceRes> getIncidenceById(Integer prmId) {
        Optional<IncidenceModel> incidenceFound = incidenceRepository.findById(prmId);

        IncidenceRes incidenceRes = incidenceFound.map(incidence -> IncidenceRes.builder()
                .id(incidence.getId())
                .name(incidence.getName())
                .teacherName(incidence.getTeacherName().getUsername())
                .incidenceType(incidence.getInsidenciaType())
                .build()).orElse(null);

        return incidenceRes != null ? Optional.of(incidenceRes) : Optional.empty();
    }

    /**
     * Elimina una incidencia por su ID.
     *
     * @param prmId El ID de la incidencia que se va a eliminar.
     * @return true si se eliminó con éxito, false si no.
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
