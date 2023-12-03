package co.edu.unicauca.SIRENABackend.controllers;

import java.util.ArrayList;
import java.util.Optional;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.SIRENABackend.dtos.response.ClassroomTypeRes;
import co.edu.unicauca.SIRENABackend.services.ClassroomTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

/**
 * Controlador REST que maneja las operaciones relacionadas con los tipos de aulas (classroom types).
 * Proporciona endpoints para obtener una lista de tipos de aulas y detalles específicos de un tipo de aula por su ID.
 */
@RestController
@RequestMapping("/classroomType")
@Tag(name = "Classroom Type", description = "Endpoints para los tipos de salones de clase")
public class ClassroomTypeController {
    @Autowired
    ClassroomTypeService classroomTypeService;

    /**
     * Obtiene una lista de todos los tipos de aulas.
     *
     * @return Una respuesta HTTP con una lista de objetos ClassroomTypeRes y el código de estado 200 (OK).
     *         Si no se encuentran tipos de aulas, devuelve un código de estado 404 (NOT FOUND).
     */
    @Operation(summary = "Obtener tipos de aulas", description = "Obtiene una lista de tipos de aulas.", responses = {
            @ApiResponse(responseCode = "200", description = "Tipos de aulas encontrados exitosamente", content = @Content(schema = @Schema(implementation = ClassroomTypeRes.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "No se encontraron tipos de aulas", content = @Content(mediaType = "application/json"))
    })
    @GetMapping()
    public ResponseEntity<ArrayList<ClassroomTypeRes>> getClassroomType() {
        ArrayList<ClassroomTypeRes> classroomTypes = this.classroomTypeService.getClassroomType();

        if (!classroomTypes.isEmpty()) {
            return new ResponseEntity<>(classroomTypes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Obtiene un tipo de aula por su ID.
     *
     * @param prmClassroomID El identificador único del tipo de aula que se desea obtener.
     * @return Una respuesta HTTP con el objeto ClassroomTypeRes encontrado y el código de estado 200 (OK)
     *         si el tipo de aula existe, o código de estado 404 (NOT FOUND) si no se encuentra.
     */
    @Operation(summary = "Obtener tipo de aula por ID", description = "Obtiene un tipo de aula por su ID.", responses = {
            @ApiResponse(responseCode = "200", description = "Tipo de aula encontrado exitosamente", content = @Content(schema = @Schema(implementation = ClassroomTypeRes.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Tipo de aula no encontrado", content = @Content(mediaType = "application/json"))
    })
    @Parameters({
            @Parameter(name = "id", description = "ID del tipo de aula a obtener", required = true, in = ParameterIn.PATH)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Optional<ClassroomTypeRes>> getClassroomTypeById(
            @PathVariable("id") Integer prmClassroomID) {
        Optional<ClassroomTypeRes> classroomType = this.classroomTypeService.getClassroomTypeById(prmClassroomID);

        if (classroomType.isPresent()) {
            return new ResponseEntity<>(classroomType, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
