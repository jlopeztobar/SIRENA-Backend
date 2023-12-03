package co.edu.unicauca.SIRENABackend.security.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import co.edu.unicauca.SIRENABackend.security.common.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO (Data Transfer Object) que representa la solicitud para un rol.
 * Contiene la información necesaria para crear un nuevo rol.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleReq {

    /**
     * Nombre del rol.
     */
    @JsonProperty("rol_name")
    private RoleEnum name;
}
