package co.edu.unicauca.SIRENABackend.security.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO (Data Transfer Object) que representa la solicitud para iniciar sesión de un usuario.
 * Contiene la información necesaria, como nombre de usuario y contraseña.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginReq {
    /**
     * Nombre de usuario para iniciar sesión.
     */
    protected String username;

    /**
     * Contraseña del usuario para iniciar sesión.
     */
    protected String password;
}
