package co.edu.unicauca.SIRENABackend.services;

import java.util.ArrayList;
import java.util.Optional;

import co.edu.unicauca.SIRENABackend.models.IncidenceModel;

public interface IncidenceService {
    ArrayList<IncidenceModel> getIncidences();

    IncidenceModel saveIncidence(IncidenceModel prmIncidence);

    Optional<IncidenceModel> getIncidenceById(Integer prmId);

    boolean deleteIncidenceById(Integer prmId);
}
