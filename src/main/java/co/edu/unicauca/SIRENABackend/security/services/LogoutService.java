package co.edu.unicauca.SIRENABackend.security.services;

import org.springframework.security.core.Authentication;

import co.edu.unicauca.SIRENABackend.security.models.UserModel;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servicio de cierre de sesión en la aplicación.
 */
public interface LogoutService {

    /**
     * Realiza el proceso de cierre de sesión, revocando tokens asociados al usuario autenticado.
     *
     * @param request        Solicitud HTTP.
     * @param response       Respuesta HTTP.
     * @param authentication Objeto de autenticación que contiene detalles del usuario autenticado.
     */
    void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication);

    /**
     * Revoca todos los tokens asociados a un usuario.
     *
     * @param prmUser Usuario al que se le revocarán los tokens.
     */
    void revokeAllUserTokens(UserModel prmUser);
}
