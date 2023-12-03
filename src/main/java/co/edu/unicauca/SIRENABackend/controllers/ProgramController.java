package co.edu.unicauca.SIRENABackend.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.SIRENABackend.models.ProgramModel;
import co.edu.unicauca.SIRENABackend.services.ProgramService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/programs")
@Tag(name = "Program controller", description = "Endpoints para las programas")
public class ProgramController {

        @Autowired
        private ProgramService programService;

        /**
         * Guarda un nuevo programa.
         *
         * @param program El objeto ProgramModel que se desea guardar.
         * @return El objeto BuildingModel guardado.
         */
        @Operation(summary = "Registra un programa", description = "Crea un programa en el sistema.", responses = {
                        @ApiResponse(responseCode = "200", description = "programa creado exitosamente", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProgramModel.class)), mediaType = "application/json")),
                        @ApiResponse(responseCode = "404", description = "No se creo el edificio", content = @Content(mediaType = "application/json"))
        })
        @PostMapping
        public ProgramModel saveProgram(@RequestBody ProgramModel program) {
                return programService.saveProgram(program);
        }

        /**
         * Obtiene todos los programas almacenados en la base de datos.
         *
         * @return Una lista de objetos ProgramModel.
         */
        @Operation(summary = "Obtener todas lo programas", description = "Obtiene una lista de todos los programas registrados en el sistema.", responses = {
                        @ApiResponse(responseCode = "200", description = "programas obtenidos exitosamente", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProgramModel.class)), mediaType = "application/json")),
                        @ApiResponse(responseCode = "404", description = "No se encontraron programas", content = @Content(mediaType = "application/json"))
        })
        @GetMapping
        public List<ProgramModel> getPrograms() {
                return programService.getPrograms();
        }

        /**
         * Obtiene un programa por su ID.
         *
         * @param id El identificador único del programa que se desea obtener.
         * @return Un objeto Optional que contiene el ProgramModel encontrado (si
         *         existe).
         */
        @Operation(summary = "Obtener un programa", description = "Obtiene un programa registrado por su id en el sistema.", responses = {
                        @ApiResponse(responseCode = "200", description = "programa obtenido exitosamente", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProgramModel.class)), mediaType = "application/json")),
                        @ApiResponse(responseCode = "404", description = "No se encontro el programa", content = @Content(mediaType = "application/json"))
        })
        @Parameters({
                        @Parameter(name = "id", description = "ID del programa a obtener", required = true, in = ParameterIn.PATH)
        })
        @GetMapping("/{id}")
        public Optional<ProgramModel> getProgramById(@PathVariable Integer id) {
                return programService.getProgramById(id);
        }

        /**
         * Elimina un programa por su ID.
         *
         * @param id El identificador único del programa que se desea eliminar.
         * @return `true` si el programa se eliminó con éxito, `false` si no se
         *         encuentra.
         */
        @Operation(summary = "Elimina un programa", description = "Elimina un programa por su id en el sistema.", responses = {
                        @ApiResponse(responseCode = "200", description = "programa eliminado exitosamente", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProgramModel.class)), mediaType = "application/json")),
                        @ApiResponse(responseCode = "404", description = "No se encontro el programa", content = @Content(mediaType = "application/json"))
        })
        @Parameters({
                        @Parameter(name = "id", description = "ID del programa a eliminar", required = true, in = ParameterIn.PATH)
        })
        @DeleteMapping("/{id}")
        public boolean deleteProgramById(@PathVariable Integer id) {
                return programService.deleteProgramById(id);
        }
}
