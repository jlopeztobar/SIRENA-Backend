package co.edu.unicauca.SIRENABackend.services.impl;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.unicauca.SIRENABackend.dtos.request.ClassroomTypeReq;
import co.edu.unicauca.SIRENABackend.dtos.response.ClassroomTypeRes;
import co.edu.unicauca.SIRENABackend.models.ClassroomTypeModel;
import co.edu.unicauca.SIRENABackend.repositories.IClassroomTypeRepository;
import co.edu.unicauca.SIRENABackend.services.ClassroomTypeService;
import lombok.RequiredArgsConstructor;

/**
 * Implementación de {@link ClassroomTypeService} que interactúa con la capa de repositorio para realizar operaciones
 * relacionadas con los tipos de aulas.
 */
@Service
@RequiredArgsConstructor
public class ClassroomTypeServiceImpl implements ClassroomTypeService {

    private final IClassroomTypeRepository classroomTypeRepository;

    /**
     * Obtiene todos los tipos de aulas en el sistema.
     *
     * @return Lista de tipos de aulas.
     */

    @Override
    @Transactional(readOnly = true)
    public ArrayList<ClassroomTypeRes> getClassroomType() {
        ArrayList<ClassroomTypeModel> classroomTypes = classroomTypeRepository.findAll();
        ArrayList<ClassroomTypeRes> classroomTypesRes = new ArrayList<>();

        for (ClassroomTypeModel classroomType : classroomTypes) {
            ClassroomTypeRes classroomTypeTemp = ClassroomTypeRes.builder()
                    .id(classroomType.getId())
                    .name(classroomType.getName())
                    .build();
            classroomTypesRes.add(classroomTypeTemp);
        }

        return classroomTypesRes;
    }

    /**
     * Guarda un nuevo tipo de aula en el sistema.
     *
     * @param classroomTypeReq La información del tipo de aula que se va a guardar.
     * @return El tipo de aula guardado o null si ya existe un tipo con el mismo nombre.
     */
    @Override
    @Transactional
    public ClassroomTypeRes saveClassroomType(ClassroomTypeReq classroomTypeReq) {
        if (classroomTypeRepository.existsByName(classroomTypeReq.getName())) {
            return null;
        }

        ClassroomTypeModel classroomType = ClassroomTypeModel.builder()
                .name(classroomTypeReq.getName())
                .build();

        ClassroomTypeModel classroomTypeSave = classroomTypeRepository.save(classroomType);

        ClassroomTypeRes classroomTypeRes = ClassroomTypeRes.builder()
                .id(classroomTypeSave.getId())
                .name(classroomTypeSave.getName())
                .build();

        return classroomTypeRes;
    }

    /**
     * Obtiene un tipo de aula por su ID.
     *
     * @param prmId El ID del tipo de aula que se va a obtener.
     * @return Un {@link Optional} que contiene el tipo de aula si se encuentra, o vacío si no.
     */
    @Transactional(readOnly = true)
    public Optional<ClassroomTypeRes> getClassroomTypeById(Integer prmId) {
        Optional<ClassroomTypeModel> classroomFound = classroomTypeRepository.findById(prmId);

        ClassroomTypeRes classroomTypeRes = classroomFound.map(classroomType -> ClassroomTypeRes.builder()
                .id(classroomType.getId())
                .name(classroomType.getName())
                .build()).orElse(null);

        return classroomTypeRes != null ? Optional.of(classroomTypeRes) : Optional.empty();
    }

    /**
     * Elimina un tipo de aula por su ID.
     *
     * @param prmId El ID del tipo de aula que se va a eliminar.
     * @return true si se eliminó con éxito, false si no.
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