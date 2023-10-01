package co.edu.unicauca.SIRENABackend.Auth;

import co.edu.unicauca.SIRENABackend.models.RoleModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    /**
     * Identificador del usuario.
     */
    protected Integer usr_id;

    /**
     * Rol del usuario.
     */
    protected RoleModel usr_role;

    /**
     * Primer nombre del usuario.
     */
    protected String usr_firstname;

    /**
     * Apellido del usuario.
     */
    protected String usr_lastname;

    /**
     * Nombre del usuario.
     */
    protected String usr_name;

    /**
     * Contraseña del usuario.
     */
    protected String usr_password;

    /**
     * Correo electrónico del usuario.
     */
    protected String usr_email;
}