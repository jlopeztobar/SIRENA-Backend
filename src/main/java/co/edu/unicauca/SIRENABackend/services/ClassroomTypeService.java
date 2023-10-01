package co.edu.unicauca.SIRENABackend.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.unicauca.SIRENABackend.models.ClassroomTypeModel;
import co.edu.unicauca.SIRENABackend.repositories.IClassroomTypeRepository;

/**
 * Servicio encargado de gestionar las operaciones relacionadas con los tipos de aulas (ClassroomTypeModel).
 */
@Service
public class ClassroomTypeService {
    @Autowired
    private IClassroomTypeRepository classroomTypeRepository;

    /**
     * Obtiene una lista de todos los tipos de aulas disponibles.
     *
     * @return ArrayList que contiene todos los tipos de aulas.
     */
    @Transactional(readOnly = true)
    public ArrayList<ClassroomTypeModel> getClassroomType() {
        return (ArrayList<ClassroomTypeModel>) classroomTypeRepository.findAll();
    }

    /**
     * Guarda un tipo de aula en la base de datos.
     *
     * @param classroomTypeModel El ClassroomTypeModel que se va a guardar.
     * @return El ClassroomTypeModel guardado.
     */
    @Transactional
    public ClassroomTypeModel saveClassroomType(ClassroomTypeModel classroomTypeModel) {
        return classroomTypeRepository.save(classroomTypeModel);
    }

    /**
     * Obtiene un tipo de aula por su ID.
     *
     * @param prmId El ID del ClassroomTypeModel que se desea recuperar.
     * @return Un Optional que contiene el ClassroomTypeModel si se encuentra, o un Optional vacío si no se encuentra.
     */
    @Transactional(readOnly = true)
    public Optional<ClassroomTypeModel> getClassroomTypeById(Integer prmId) {
        return classroomTypeRepository.findById(prmId);
    }

    /**
     * Elimina un tipo de aula por su ID.
     *
     * @param prmId El ID del ClassroomTypeModel que se desea eliminar.
     * @return True si la eliminación es exitosa, false en caso contrario.
     */
    @Transactional
    public boolean deleteClassroomTypeById(Integer prmId) {
        try {
            classroomTypeRepository.deleteById(prmId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}