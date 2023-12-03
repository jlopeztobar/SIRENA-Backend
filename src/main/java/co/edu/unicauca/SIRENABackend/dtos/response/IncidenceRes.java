package co.edu.unicauca.SIRENABackend.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import co.edu.unicauca.SIRENABackend.models.IncidenceTypeModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Data Transfer Object (DTO) que representa la respuesta de una incidencia.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncidenceRes {
    /**
     * Identificador único de la incidencia.
     */
    @JsonProperty("ins_id")
    private Integer id;
    /**
     * Nombre de la incidencia.
     */
    @JsonProperty("ins_name")
    private String name;
    /**
     * Nombre del profesor asociado a la incidencia.
     */
    @JsonProperty("ins_teacher_name")
    private String teacherName;
    /**
     * Tipo de incidencia asociada.
     */
    @JsonProperty("ins_type")
    private IncidenceTypeModel incidenceType;
}
