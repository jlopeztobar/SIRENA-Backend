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

import co.edu.unicauca.SIRENABackend.dtos.response.IncidenceTypeRes;
import co.edu.unicauca.SIRENABackend.services.IncidenceTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

/**
 * Controlador REST que maneja las operaciones relacionadas con los tipos de incidencia.
 * Proporciona endpoints para obtener una lista de tipos de incidencia y obtener un tipo de incidencia por su ID.
 */
@RestController
@RequestMapping("/incidenceType")
@Tag(name = "Incidence Type", description = "Endpoints para los tipos de incidencias")
public class IncidenceTypeController {
    @Autowired
    IncidenceTypeService incidenceTypeService;

    /**
     * Obtiene una lista de todos los tipos de incidencia.
     *
     * @return Una respuesta HTTP con una lista de objetos IncidenceTypeRes y el código de estado 200 (OK).
     *         Si no se encuentran tipos de incidencia, devuelve un código de estado 404 (NOT FOUND).
     */
    @Operation(summary = "Obtener tipos de incidencia", description = "Obtiene una lista de tipos de incidencia.", responses = {
            @ApiResponse(responseCode = "200", description = "Tipos de incidencia encontrados exitosamente", content = @Content(schema = @Schema(implementation = IncidenceTypeRes.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "No se encontraron tipos de incidencia", content = @Content(mediaType = "application/json"))
    })
    @GetMapping()
    public ResponseEntity<ArrayList<IncidenceTypeRes>> getIncidenceTypes() {
        ArrayList<IncidenceTypeRes> incidenceTypes = incidenceTypeService.getIncidenceTypes();

        if (!incidenceTypes.isEmpty()) {
            return new ResponseEntity<>(incidenceTypes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Obtiene un tipo de incidencia por su ID.
     *
     * @param incidenceTypeID El identificador único del tipo de incidencia que se desea obtener.
     * @return Una respuesta HTTP con el objeto IncidenceTypeRes encontrado y el código de estado 200 (OK)
     *         si el tipo de incidencia existe, o código de estado 404 (NOT FOUND) si no se encuentra.
     */
    @Operation(summary = "Obtener tipo de incidencia por ID", description = "Obtiene un tipo de incidencia por su ID.", responses = {
            @ApiResponse(responseCode = "200", description = "Tipo de incidencia encontrado exitosamente", content = @Content(schema = @Schema(implementation = IncidenceTypeRes.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Tipo de incidencia no encontrado", content = @Content(mediaType = "application/json"))
    })
    @Parameters({
            @Parameter(name = "id", description = "ID del tipo de incidencia a obtener", required = true, in = ParameterIn.PATH)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Optional<IncidenceTypeRes>> getIncidenceTypeById(
            @PathVariable("id") Integer incidenceTypeID) {
        Optional<IncidenceTypeRes> incidenceType = this.incidenceTypeService.getIncidenceTypeById(incidenceTypeID);

        if (incidenceType.isPresent()) {
            return new ResponseEntity<>(incidenceType, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
