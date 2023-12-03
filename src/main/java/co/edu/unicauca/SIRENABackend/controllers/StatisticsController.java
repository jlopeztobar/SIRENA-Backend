package co.edu.unicauca.SIRENABackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.SIRENABackend.models.ProgramModel;
import co.edu.unicauca.SIRENABackend.models.StatisticsModel;
import co.edu.unicauca.SIRENABackend.services.StatisticsService;
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
 * Controlador REST que maneja las operaciones relacionadas con las
 * estadisticas.
 */
@RestController
@RequestMapping("/statistics")
@Tag(name = "Statistics controller", description = "Endpoints para las estadisticas")
public class StatisticsController {
    @Autowired
    private StatisticsService statisticsService;

    @Operation(summary = "Obtener las estadisticas de los salones", description = "Obtiene la id de todas las reservas asociadas a un salon")
    @GetMapping("/classroom")
    public List<StatisticsModel> getClassroomsStatistics() {
        return statisticsService.getClassroomsStatistics();
    }

    @Operation(summary = "Obtener las estadisticas de los edificios", description = "Obtiene la id de todas las reservas asociadas a un edificio")
    @GetMapping("/building")
    public List<StatisticsModel> getBuildingsStatistics() {
        return statisticsService.getBuildingsStatistics();
    }

    @Operation(summary = "Obtener las estadisticas de las facultades", description = "Obtiene la id de todas las reservas asociadas a una facultad")
    @GetMapping("/faculty")
    public List<StatisticsModel> getFacultiesStatistics() {
        return statisticsService.getFacultiesStatistics();
    }

    @Operation(summary = "Obtener las estadisticas de los programas", description = "Obtiene la id de todas las reservas asociadas a un programa")
    @GetMapping("/programs")
    public List<StatisticsModel> getProgramsStatistics() {
        return statisticsService.getProgramsStatistics();
    }

    @Operation(summary = "Obtener los programas asociados a una facultad", description = "Obtiene todos los programas de una facultad a traves de su id", responses = {
            @ApiResponse(responseCode = "200", description = "Programas obtenidos exitosamente", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProgramModel.class)), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "No se encontro la facultad", content = @Content(mediaType = "application/json"))
    })
    @Parameters({
            @Parameter(name = "id", description = "ID de la facultad a obtener", required = true, in = ParameterIn.PATH)
    })
    @GetMapping("/programs/{id}")
    public List<ProgramModel> getProgramsFacultie(@PathVariable Integer id) {
        return statisticsService.getProgramsFacultie(id);
    }
}
