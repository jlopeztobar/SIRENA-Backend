package co.edu.unicauca.SIRENABackend.security.dtos.request;

import co.edu.unicauca.SIRENABackend.security.common.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO (Data Transfer Object) que representa la solicitud para registrar un nuevo usuario.
 * Contiene la información necesaria, como nombre, apellidos, correo electrónico, contraseña y rol del usuario.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterReq {

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
     * Contraseña del usuario.
     */
    protected String usr_password;

    /**
     * Rol del usuario.
     */
    protected RoleEnum usr_role;
}