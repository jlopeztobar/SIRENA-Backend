package co.edu.unicauca.SIRENABackend.controllers;

import java.util.List;
import java.util.Optional;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

/**
 * Controlador REST que maneja las operaciones relacionadas con las aulas (classrooms).
 * Proporciona endpoints para la creación, recuperación, actualización y eliminación de aulas.
 * Además, incluye operaciones para obtener listas de aulas y detalles específicos de una aula por su ID.
 */
@RestController
@RequestMapping("/classroom")
@Tag(name = "Classroom controller", description = "Endpoints para los salones de clase")
public class ClassroomController {
    @Autowired
    private ClassroomService ClassroomService;

    /**
     * Crea una nueva aula.
     *
     * @param classroom El objeto ClassroomModel que se desea crear y guardar.
     * @return Una respuesta HTTP con el objeto ClassroomModel creado y el código de
     *         estado 201 (CREATED).
     */
    @Operation(summary = "Create a new classroom", description = "Creates a new classroom.", responses = {
            @ApiResponse(responseCode = "201", description = "Classroom created successfully", content = @Content(schema = @Schema(implementation = ClassroomModel.class))),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping()
    public ResponseEntity<ClassroomModel> save(@RequestBody ClassroomModel classroom) {
        ClassroomModel savedClassroom = this.ClassroomService.save(classroom);
        if(savedClassroom!=null)
            return new ResponseEntity<>(savedClassroom, HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * Obtiene una lista de todas las aulas almacenadas en la base de datos.
     *
     * @return Una respuesta HTTP con una lista de objetos ClassroomModel y el
     *         código de estado 200 (OK).
     */
    @Operation(summary = "Get a list of classrooms", description = "Retrieves a list of all classrooms.", responses = {
            @ApiResponse(responseCode = "200", description = "Classrooms retrieved successfully", content = @Content(schema = @Schema(implementation = ClassroomModel.class))),
            @ApiResponse(responseCode = "404", description = "Classrooms not found")
    })
    @GetMapping
    public ResponseEntity<List<ClassroomModel>> classrooms() {
        List<ClassroomModel> classroomList = this.ClassroomService.findAll();
        return new ResponseEntity<>(classroomList, HttpStatus.OK);
    }

    /**
     * Obtiene un aula por su ID.
     *
     * @param id El identificador único del aula que se desea obtener.
     * @return Una respuesta HTTP con el objeto ClassroomModel encontrado y el
     *         código de estado 200 (OK) si el aula existe, o código de estado 404
     *         (NOT FOUND) si no se encuentra.
     */
    @Operation(summary = "Get a classroom by ID", description = "Retrieves a classroom by its ID.", responses = {
            @ApiResponse(responseCode = "200", description = "Classroom retrieved successfully", content = @Content(schema = @Schema(implementation = ClassroomModel.class))),
            @ApiResponse(responseCode = "404", description = "Classroom not found")
    })
    @Parameters({
            @Parameter(name = "id", description = "ID del classroom", required = true, in = ParameterIn.PATH)
    })
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
     * Actualiza un aula por su ID.
     *
     * @param classroom El objeto ClassroomModel con los datos actualizados.
     * @param id        El identificador único del aula que se desea actualizar.
     * @return Una respuesta HTTP con el objeto ClassroomModel actualizado y el
     *         código de estado 200 (OK) si el aula existe, o código de estado 404
     *         (NOT FOUND) si no se encuentra.
     */
    @Operation(summary = "Update a classroom by ID", description = "Updates a classroom by its ID.", responses = {
            @ApiResponse(responseCode = "200", description = "Classroom updated successfully", content = @Content(schema = @Schema(implementation = ClassroomModel.class))),
            @ApiResponse(responseCode = "404", description = "Classroom not found")
    })
    @Parameters({
            @Parameter(name = "id", description = "ID del classroom", required = true, in = ParameterIn.PATH)
    })
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
     * Elimina un aula por su ID.
     *
     * @param id El identificador único del aula que se desea eliminar.
     * @return Una respuesta HTTP con el código de estado 204 (NO CONTENT) si el
     *         aula se eliminó con éxito, o código de estado 404 (NOT FOUND) si no se
     *         encuentra.
     */
    @Operation(summary = "Delete a classroom by ID", description = "Deletes a classroom by its ID.", responses = {
            @ApiResponse(responseCode = "204", description = "Classroom deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Classroom not found")
    })
    @Parameters({
            @Parameter(name = "id", description = "ID del classroom", required = true, in = ParameterIn.PATH)
    })
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
