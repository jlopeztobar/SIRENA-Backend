package co.edu.unicauca.SIRENABackend.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) que representa la solicitud para crear una nueva facultad.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FacultyRequest {

    /**
     * Nombre de la nueva facultad.
     */
    @JsonProperty("fac_name")
    private String name;

    /**
     * Identificador único del edificio al que pertenece la facultad.
     */
    @JsonProperty("fac_building_id")
    private int buildingId;
}
