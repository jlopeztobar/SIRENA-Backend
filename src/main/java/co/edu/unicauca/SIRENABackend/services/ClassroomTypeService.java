package co.edu.unicauca.SIRENABackend.services;

import java.util.ArrayList;
import java.util.Optional;

import co.edu.unicauca.SIRENABackend.models.ClassroomTypeModel;

public interface ClassroomTypeService {
    ArrayList<ClassroomTypeModel> getClassroomType();

    ClassroomTypeModel saveClassroomType(ClassroomTypeModel classroomTypeModel);

    Optional<ClassroomTypeModel> getClassroomTypeById(Integer prmId);

    boolean deleteClassroomTypeById(Integer prmId);
}
