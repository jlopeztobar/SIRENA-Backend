package co.edu.unicauca.SIRENABackend.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.unicauca.SIRENABackend.models.FacultyModel;
import co.edu.unicauca.SIRENABackend.repositories.IFacultyRepository;
import co.edu.unicauca.SIRENABackend.services.FacultyService;

/**
 * Implementación de {@link FacultyService} que interactúa con la capa de repositorio para realizar operaciones
 * relacionadas con las facultades.
 */
@Service
public class FacultyServiceImpl implements FacultyService {


    @Autowired
    private IFacultyRepository facultyReposirtory;

    /**
     * Guarda una nueva facultad en el sistema.
     *
     * @param faculty La facultad que se va a guardar.
     * @return La facultad guardada.
     */
    @Transactional(readOnly = false)
    public FacultyModel saveFaculties(FacultyModel faculty) {
        if(faculty.getBuilding()!=null)
        {
            return facultyReposirtory.save(faculty);
        }
        return null;
    }

    /**
     * Elimina una facultad por su ID.
     *
     * @param id El ID de la facultad que se va a eliminar.
     * @return true si se eliminó con éxito, false si no.
     */
    @Transactional(readOnly = false)
    public boolean deleteFacultyById(Integer id) {
        try {
            facultyReposirtory.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Obtiene una facultad por su ID.
     *
     * @param id El ID de la facultad que se va a obtener.
     * @return Un {@link Optional} que contiene la facultad si se encuentra, o vacío si no.
     */
    @Transactional(readOnly = true)
    public Optional<FacultyModel> getFacultyById(Integer id) {
        return facultyReposirtory.findById(id);
    }

    /**
     * Obtiene todas las facultades en el sistema.
     *
     * @return Lista de facultades.
     */
    @Transactional(readOnly = true)
    public List<FacultyModel> getFaculties() {
        return (List<FacultyModel>) facultyReposirtory.findAll();
    }
}
