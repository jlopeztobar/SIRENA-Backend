package co.edu.unicauca.SIRENABackend.services;

import java.util.ArrayList;
import java.util.Optional;

import co.edu.unicauca.SIRENABackend.dtos.request.ClassroomTypeReq;
import co.edu.unicauca.SIRENABackend.dtos.response.ClassroomTypeRes;

/**
 * Interfaz que define los servicios relacionados con los tipos de aula en el sistema.
 */
public interface ClassroomTypeService {

    /**
     * Obtiene todos los tipos de aula en el sistema.
     *
     * @return Lista de todos los tipos de aula.
     */
    ArrayList<ClassroomTypeRes> getClassroomType();

    /**
     * Guarda un nuevo tipo de aula en el sistema.
     *
     * @param classroomTypeReq La solicitud de tipo de aula que se va a guardar.
     * @return El tipo de aula guardado.
     */
    ClassroomTypeRes saveClassroomType(ClassroomTypeReq classroomTypeReq);

    /**
     * Obtiene un tipo de aula por su ID.
     *
     * @param prmId El ID del tipo de aula que se va a obtener.
     * @return Un {@link Optional} que contiene el tipo de aula si se encuentra, o vacío si no.
     */
    Optional<ClassroomTypeRes> getClassroomTypeById(Integer prmId);

    /**
     * Elimina un tipo de aula por su ID.
     *
     * @param prmId El ID del tipo de aula que se va a eliminar.
     * @return `true` si la eliminación fue exitosa, `false` si no.
     */
    boolean deleteClassroomTypeById(Integer prmId);
}