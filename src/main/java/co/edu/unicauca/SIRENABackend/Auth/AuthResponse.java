package co.edu.unicauca.SIRENABackend.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    /**
     * Token de autenticación generado para el usuario.
     */
    String token; 
}
