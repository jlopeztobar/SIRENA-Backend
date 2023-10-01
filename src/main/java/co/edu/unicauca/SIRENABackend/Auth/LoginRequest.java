package co.edu.unicauca.SIRENABackend.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    /**
     * Nombre de usuario para la autenticación.
     */
    protected String username;

    /**
     * Contraseña asociada al nombre de usuario.
     */
    protected String password; 
}

