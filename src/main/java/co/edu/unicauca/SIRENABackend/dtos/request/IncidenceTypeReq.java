package co.edu.unicauca.SIRENABackend.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import co.edu.unicauca.SIRENABackend.common.enums.IncidenceTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) que representa la solicitud para crear un nuevo tipo de incidencia.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncidenceTypeReq {

    /**
     * Nombre del nuevo tipo de incidencia.
     */
    @JsonProperty("ins_type_name")
    private IncidenceTypeEnum name;
}
