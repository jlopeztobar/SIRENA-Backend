package co.edu.unicauca.SIRENABackend.services.impl;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.unicauca.SIRENABackend.models.IncidenceModel;
import co.edu.unicauca.SIRENABackend.repositories.IIncidenceRepository;
import co.edu.unicauca.SIRENABackend.services.IncidenceService;

@Service
public class IncidenceServiceImpl implements IncidenceService{

    @Autowired
    private IIncidenceRepository incidenceRepository;

    @Transactional(readOnly = true)
    public ArrayList<IncidenceModel> getIncidences() {
        return (ArrayList<IncidenceModel>) incidenceRepository.findAll();
    }

    @Transactional
    public IncidenceModel saveIncidence(IncidenceModel prmIncidence) {
        return incidenceRepository.save(prmIncidence);
    }

    @Transactional(readOnly = true)
    public Optional<IncidenceModel> getIncidenceById(Integer prmId) {
        return incidenceRepository.findById(prmId);
    }

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
