package co.edu.unicauca.SIRENABackend.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.SIRENABackend.models.ClassroomModel;
import co.edu.unicauca.SIRENABackend.services.ClassroomService;

@RestController
@RequestMapping("/classroom")
public class ClassroomController {
    @Autowired
    private ClassroomService ClassroomService;

    /**
     * Crea una nueva instancia de aula (classroom) y la guarda en la base de datos.
     *
     * @param classroom El objeto ClassroomModel que se desea crear y guardar.
     * @return La instancia de ClassroomModel creada y almacenada en la base de
     *         datos.
     */
    @PostMapping()
    public ResponseEntity<ClassroomModel> save(@RequestBody ClassroomModel classroom) {
        ClassroomModel savedClassroom = this.ClassroomService.save(classroom);
        return new ResponseEntity<>(savedClassroom, HttpStatus.CREATED);
    }

    /**
     * Obtiene una lista de todas las aulas registradas.
     *
     * @return Una lista de objetos ClassroomModel que representan todas las aulas.
     */
    @GetMapping
    public ResponseEntity<List<ClassroomModel>> classrooms() {
        List<ClassroomModel> classroomList = this.ClassroomService.findAll();
        return new ResponseEntity<>(classroomList, HttpStatus.OK);
    }

    /**
     * Obtiene una aula por su identificador único.
     *
     * @param id El identificador único del aula que se desea obtener.
     * @return El objeto ClassroomModel encontrado, o null si no se encuentra.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ClassroomModel> show(@PathVariable Integer id) {
        Optional<ClassroomModel> classroomOptional = this.ClassroomService.findById(id);

        if (classroomOptional.isPresent()) {
            ClassroomModel classroom = classroomOptional.get();
            return new ResponseEntity<>(classroom, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Actualiza una aula existente por su identificador único.
     *
     * @param classroom El objeto ClassroomModel con los datos actualizados.
     * @param id        El identificador único del aula que se desea actualizar.
     * @return La instancia de ClassroomModel actualizada y almacenada en la base de
     *         datos.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ClassroomModel> update(@RequestBody ClassroomModel classroom, @PathVariable Integer id) {
        Optional<ClassroomModel> classroomCurrentOptional = this.ClassroomService.findById(id);

        if (classroomCurrentOptional.isPresent()) {
            ClassroomModel classroomCurrent = classroomCurrentOptional.get();
            classroomCurrent.setName(classroom.getName());
            classroomCurrent.setBuilding(classroom.getBuilding());
            classroomCurrent.setCapacity(classroom.getCapacity());
            classroomCurrent.setState(classroom.getState());
            classroomCurrent.setClassroomType(classroom.getClassroomType());

            ClassroomModel updatedClassroom = ClassroomService.save(classroomCurrent);
            return new ResponseEntity<>(updatedClassroom, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Elimina un aula por su identificador único.
     *
     * @param id El identificador único del aula que se desea eliminar.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Optional<ClassroomModel> classroomOptional = this.ClassroomService.findById(id);

        if (classroomOptional.isPresent()) {
            ClassroomService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}