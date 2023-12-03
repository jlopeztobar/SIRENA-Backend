package co.edu.unicauca.SIRENABackend.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import co.edu.unicauca.SIRENABackend.common.enums.IncidenceTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Data Transfer Object (DTO) que representa la respuesta de un tipo de incidencia.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncidenceTypeRes {

    /**
     * Identificador único del tipo de incidencia.
     */
    @JsonProperty("ins_type_id")
    private Integer id;

    /**
     * Nombre del tipo de incidencia.
     */
    @JsonProperty("ins_type_name")
    private IncidenceTypeEnum name;
}
