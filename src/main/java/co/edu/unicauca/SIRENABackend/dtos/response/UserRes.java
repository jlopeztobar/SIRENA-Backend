package co.edu.unicauca.SIRENABackend.dtos.response;

import com.fasterxml.jackson.annotation.JsonAlias;

import co.edu.unicauca.SIRENABackend.security.common.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) que representa la respuesta de un usuario.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRes {

    /**
     * Identificador único del usuario.
     */
    @JsonAlias("usr_id")
    private Integer id;

    /**
     * Nombres del usuario.
     */
    @JsonAlias("usr_username")
    private String username;

    /**
     * Role del usuario.
     */
    @JsonAlias("usr_role")
    private RoleEnum role;
}
