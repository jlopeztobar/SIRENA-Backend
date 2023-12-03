package co.edu.unicauca.SIRENABackend.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import co.edu.unicauca.SIRENABackend.common.enums.ClassroomTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) que representa la respuesta de un tipo de salón de clases.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassroomTypeRes {
    /**
     * Identificador único del tipo de salón de clases.
     */
    @JsonProperty("cls_type_id")
    private Integer id;

    /**
     * Nombre del tipo de salón de clases (ej. aula, laboratorio, auditorio, etc.).
     */
    @JsonProperty("cls_type_name")
    private ClassroomTypeEnum name;
}
