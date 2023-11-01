package co.edu.unicauca.SIRENABackend.services;

import java.util.List;
import java.util.Optional;

import co.edu.unicauca.SIRENABackend.models.ClassroomModel;

public interface ClassroomService {
    ClassroomModel save(ClassroomModel classroom);

    void delete(Integer id);

    Optional<ClassroomModel> findById(Integer id);

    List<ClassroomModel> findAll();
}
