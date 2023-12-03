package co.edu.unicauca.SIRENABackend.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import co.edu.unicauca.SIRENABackend.common.enums.IncidenceTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) que representa la solicitud para crear una nueva incidencia.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncidenceReq {

    /**
     * Nombre de la nueva incidencia.
     */
    @JsonProperty("ins_name")
    private String name;

    /**
     * Nombre del profesor asociado a la incidencia.
     */
    @JsonProperty("ins_teacher_name")
    private String teacherName;

    /**
     * Tipo de la incidencia (Defecto o Inconveniente).
     */
    @JsonProperty("ins_type")
    private IncidenceTypeEnum incidenceType;
}
