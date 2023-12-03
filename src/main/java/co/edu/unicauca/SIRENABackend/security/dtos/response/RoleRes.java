package co.edu.unicauca.SIRENABackend.security.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import co.edu.unicauca.SIRENABackend.security.common.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO (Data Transfer Object) que representa la respuesta para un rol, incluyendo su identificador y nombre.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleRes {

    /**
     * Identificador del rol.
     */
    @JsonProperty("rol_id")
    private Integer id;

    /**
     * Nombre del rol.
     */
    @JsonProperty("rol_name")
    private RoleEnum name;
}
