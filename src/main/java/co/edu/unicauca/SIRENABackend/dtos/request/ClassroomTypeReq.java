package co.edu.unicauca.SIRENABackend.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import co.edu.unicauca.SIRENABackend.common.enums.ClassroomTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) que representa la solicitud para crear un nuevo tipo de aula.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassroomTypeReq {
    /**
     * Nombre del nuevo tipo de aula.
     */
    @JsonProperty("cls_type_name")
    private ClassroomTypeEnum name;
}
