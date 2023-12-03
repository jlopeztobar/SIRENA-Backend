package co.edu.unicauca.SIRENABackend.security.dtos.response;

import co.edu.unicauca.SIRENABackend.security.common.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO (Data Transfer Object) que representa la respuesta para el registro de un usuario,
 * incluyendo información como el identificador, nombre, apellidos, correo electrónico, estado y rol.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRes {
    /**
     * Identificador del usuario.
     */
    protected Integer usr_id;

    /**
     * Nombre del usuario.
     */
    protected String usr_name;

    /**
     * Primer nombre del usuario.
     */
    protected String usr_firstname;

    /**
     * Apellidos del usuario.
     */
    protected String usr_lastname;

    /**
     * Correo electrónico del usuario.
     */
    protected String usr_email;

    /**
     * Estado del usuario (activo o inactivo).
     */
    protected Boolean usr_status;

    /**
     * Rol del usuario.
     */
    protected RoleEnum usr_role;
}
