package co.edu.unicauca.SIRENABackend.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.SIRENABackend.models.ClassroomTypeModel;
import co.edu.unicauca.SIRENABackend.services.ClassroomTypeService;

@RestController
@RequestMapping("/classroomType")
public class ClassroomTypeController {
    @Autowired
    ClassroomTypeService classroomTypeService;

    @PostMapping(value = "/test")
    public String demo() {
        return "demo testing";
    }

    /**
     * Obtiene una lista de tipos de aulas.
     *
     * @return ArrayList de ClassroomTypeModel que representa los tipos de aulas.
     */
    @GetMapping()
    public ResponseEntity<ArrayList<ClassroomTypeModel>> getClassroomType() {
        ArrayList<ClassroomTypeModel> classroomTypes = this.classroomTypeService.getClassroomType();

        if (!classroomTypes.isEmpty()) {
            return new ResponseEntity<>(classroomTypes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Guarda un nuevo tipo de aula.
     *
     * @param prmClassroom El tipo de aula a guardar.
     * @return El ClassroomTypeModel que ha sido guardado.
     */
    @PostMapping()
    public ResponseEntity<ClassroomTypeModel> saveClassroomType(@RequestBody ClassroomTypeModel prmClassroom) {
        ClassroomTypeModel savedClassroomType = this.classroomTypeService.saveClassroomType(prmClassroom);

        if (savedClassroomType != null) {
            return new ResponseEntity<>(savedClassroomType, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Obtiene un tipo de aula por su ID.
     *
     * @param prmClassroomID El ID del tipo de aula a obtener.
     * @return Un Optional que puede contener un ClassroomTypeModel si se encuentra,
     *         de lo contrario, es vacío.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Optional<ClassroomTypeModel>> getClassroomTypeById(
            @PathVariable("id") Integer prmClassroomID) {
        Optional<ClassroomTypeModel> classroomType = this.classroomTypeService.getClassroomTypeById(prmClassroomID);

        if (classroomType.isPresent()) {
            return new ResponseEntity<>(classroomType, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Elimina un tipo de aula por su ID.
     *
     * @param prmClassroomID El ID del tipo de aula a eliminar.
     * @return Un mensaje que indica si se ha eliminado correctamente el tipo de
     *         aula o no.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClassroomTypeById(@PathVariable("id") Integer prmClassroomID) {
        boolean confirmation = this.classroomTypeService.deleteClassroomTypeById(prmClassroomID);

        if (confirmation) {
            return new ResponseEntity<>("Se ha eliminado la incidencia con id = " + prmClassroomID, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se eliminó la incidencia con id = " + prmClassroomID, HttpStatus.NOT_FOUND);
        }
    }

}
