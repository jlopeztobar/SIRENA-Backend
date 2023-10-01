package co.edu.unicauca.SIRENABackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.unicauca.SIRENABackend.models.ClassroomModel;
import co.edu.unicauca.SIRENABackend.repositories.IClassroomRepository;

/**
 * Implementación de la interfaz ClassRoomService para operaciones relacionadas con aulas (classrooms).
 */
@Service
public class ClassroomService {

    @Autowired
    private IClassroomRepository classroomDao;

    /**
     * Guarda una instancia de ClassroomModel en la base de datos.
     *
     * @param classroom El objeto ClassroomModel que se desea guardar.
     * @return La instancia de ClassroomModel guardada en la base de datos.
     */
    @Transactional(readOnly = false)
    public ClassroomModel save(ClassroomModel classroom) {
        return classroomDao.save(classroom);
    }

    /**
     * Elimina una aula por su identificador único.
     *
     * @param id El identificador único del aula que se desea eliminar.
     */
    @Transactional(readOnly = false)
    public void delete(Integer id) {
        classroomDao.deleteById(id);
    }

    /**
     * Busca una aula por su identificador único.
     *
     * @param id El identificador único del aula que se desea buscar.
     * @return El objeto ClassroomModel encontrado, o null si no se encuentra.
     */
    @Transactional(readOnly = true)
    public ClassroomModel findById(Integer id) {
        return classroomDao.findById(id).orElse(null);
    }

    /**
     * Obtiene una lista de todas las aulas en la base de datos.
     *
     * @return Una lista de objetos ClassroomModel que representan todas las aulas.
     */
    @Transactional(readOnly = true)
    public List<ClassroomModel> findAll() {
        return (List<ClassroomModel>) classroomDao.findAll();
    }
}
