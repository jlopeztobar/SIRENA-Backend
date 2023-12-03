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

import co.edu.unicauca.SIRENABackend.models.BuildingModel;
import co.edu.unicauca.SIRENABackend.services.BuildingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controlador REST que maneja las operaciones relacionadas con los edificios
 * (buildings).
 */
@RestController
@RequestMapping("/building")
@Tag(name = "Building controller", description = "Endpoints para los edificios")
public class BuildingController {
        @Autowired
        private BuildingService buildingService;

        /**
         * Guarda un nuevo edificio.
         *
         * @param building El objeto BuildingModel que se desea guardar.
         * @return El objeto BuildingModel guardado.
         */
        @Operation(summary = "Registra un edificio", description = "Crea un edificio en el sistema.", responses = {
                        @ApiResponse(responseCode = "200", description = "Edificio creado exitosamente", content = @Content(array = @ArraySchema(schema = @Schema(implementation = BuildingModel.class)), mediaType = "application/json")),
                        @ApiResponse(responseCode = "404", description = "No se creo el edificio", content = @Content(mediaType = "application/json"))
        })
        @PostMapping
        public BuildingModel saveBuilding(@RequestBody BuildingModel building) {
                return buildingService.saveBuilding(building);
        }

        /**
         * Obtiene todos los edificios almacenados en la base de datos.
         *
         * @return Una lista de objetos BuildingModel.
         */
        @Operation(summary = "Obtener todas lo edificios", description = "Obtiene una lista de todos los edificios registrados en el sistema.", responses = {
                        @ApiResponse(responseCode = "200", description = "Edificios obtenidos exitosamente", content = @Content(array = @ArraySchema(schema = @Schema(implementation = BuildingModel.class)), mediaType = "application/json")),
                        @ApiResponse(responseCode = "404", description = "No se encontraron edificios", content = @Content(mediaType = "application/json"))
        })
        @GetMapping
        public List<BuildingModel> getBuilding() {
                return buildingService.getBuildings();
        }

        /**
         * Obtiene un edificio por su ID.
         *
         * @param id El identificador único del edificio que se desea obtener.
         * @return Un objeto Optional que contiene el BuildingModel encontrado (si
         *         existe).
         */
        @Operation(summary = "Obtener un edificio", description = "Obtiene un edificio registrado por su id en el sistema.", responses = {
                        @ApiResponse(responseCode = "200", description = "Edificio obtenido exitosamente", content = @Content(array = @ArraySchema(schema = @Schema(implementation = BuildingModel.class)), mediaType = "application/json")),
                        @ApiResponse(responseCode = "404", description = "No se encontro el edificio", content = @Content(mediaType = "application/json"))
        })
        @Parameters({
                        @Parameter(name = "id", description = "ID del edificio a obtener", required = true, in = ParameterIn.PATH)
        })
        @GetMapping("/{id}")
        public Optional<BuildingModel> getBuildingById(@PathVariable Integer id) {
                return buildingService.getBuildingById(id);
        }

        /**
         * Elimina un edificio por su ID.
         *
         * @param id El identificador único del edificio que se desea eliminar.
         * @return `true` si el edificio se eliminó con éxito, `false` si no se
         *         encuentra.
         */
        @Operation(summary = "Elimina un edificio", description = "Elimina un edificio por su id en el sistema.", responses = {
                        @ApiResponse(responseCode = "200", description = "Edificio eliminado exitosamente", content = @Content(array = @ArraySchema(schema = @Schema(implementation = BuildingModel.class)), mediaType = "application/json")),
                        @ApiResponse(responseCode = "404", description = "No se encontro el edificio", content = @Content(mediaType = "application/json"))
        })
        @Parameters({
                        @Parameter(name = "id", description = "ID del edificio a eliminar", required = true, in = ParameterIn.PATH)
        })
        @DeleteMapping("/{id}")
        public boolean deleteBuildingById(@PathVariable Integer id) {
                return buildingService.deleteBuildingById(id);
        }
}
