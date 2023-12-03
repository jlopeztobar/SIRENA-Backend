package co.edu.unicauca.SIRENABackend.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.unicauca.SIRENABackend.models.ClassroomModel;
import co.edu.unicauca.SIRENABackend.repositories.IClassroomRepository;
import co.edu.unicauca.SIRENABackend.services.ClassroomService;

/**
 * Implementación de {@link ClassroomService} que interactúa con la capa de repositorio para realizar operaciones
 * relacionadas con las aulas.
 */
@Service
public class ClassroomServiceImpl implements ClassroomService{

    /**
     * Constructor utilizado para la inyección de dependencias.
     *
     * @param classroomRepository Repositorio de aulas.
     */
    @Autowired
    private IClassroomRepository classroomDao;

    /**
     * Guarda una aula en el sistema.
     *
     * @param classroom El aula que se va a guardar.
     * @return El aula guardada.
     */
    @Transactional(readOnly = false)
    public ClassroomModel save(ClassroomModel classroom) {
        if(classroom.getCapacity()>0 && classroom.getCapacity()<999 && !classroom.getName().isEmpty())
            return classroomDao.save(classroom);
        return null;
    }

    /**
     * Elimina un aula por su ID.
     *
     * @param id El ID del aula que se va a eliminar.
     */
    @Transactional(readOnly = false)
    public void delete(Integer id) {
        classroomDao.deleteById(id);
    }

    /**
     * Obtiene un aula por su ID.
     *
     * @param id El ID del aula que se va a obtener.
     * @return Un {@link Optional} que contiene el aula si se encuentra, o vacío si no.
     */
    @Transactional(readOnly = true)
    public Optional<ClassroomModel> findById(Integer id) {
        return classroomDao.findById(id);
    }


    /**
     * Obtiene todas las aulas en el sistema.
     *
     * @return Lista de todas las aulas.
     */
    @Transactional(readOnly = true)
    public List<ClassroomModel> findAll() {
        return (List<ClassroomModel>) classroomDao.findAll();
    }
}
