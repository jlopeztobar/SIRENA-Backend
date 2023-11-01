package co.edu.unicauca.SIRENABackend.services;

import java.util.ArrayList;
import java.util.Optional;

import co.edu.unicauca.SIRENABackend.models.IncidenceTypeModel;

public interface IncidenceTypeService {
    ArrayList<IncidenceTypeModel> getIncidenceTypes();

    IncidenceTypeModel saveIncidenceTypes(IncidenceTypeModel prmIncidence);

    Optional<IncidenceTypeModel> getIncidenceTypeById(Integer prmId);

    boolean deleteIncidenceTypeById(Integer prmId);
}
