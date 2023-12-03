package co.edu.unicauca.SIRENABackend.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidad que representa estadísticas en el sistema")
public class StatisticsModel {

    @Schema(description = "Nombre de la entidad", example = "Ciencias contables")
    private String name;

    @Schema(description = "Numero de reservas aceptadas", example = "3")
    private Integer reservas_aceptadas=0;

    @Schema(description = "Numero de reservas rechazadas", example = "3")
    private Integer reservas_rechazadas=0;

    @Schema(description = "Numero de reservas pendientes", example = "3")
    private Integer reservas_pendientes=0;
}
