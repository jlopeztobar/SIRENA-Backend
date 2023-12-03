package co.edu.unicauca.SIRENABackend.security.services;

import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;

import co.edu.unicauca.SIRENABackend.security.dtos.request.UserLoginReq;
import co.edu.unicauca.SIRENABackend.security.dtos.request.UserRegisterReq;
import co.edu.unicauca.SIRENABackend.security.dtos.response.AuthTokenRes;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servicio de autenticación y autorización en la aplicación.
 */
public interface AuthService {

    /**
     * Realiza el proceso de inicio de sesión.
     *
     * @param request Datos de inicio de sesión.
     * @return Token de autenticación y token de actualización.
     */
    AuthTokenRes login(UserLoginReq request);

    /**
     * Registra un nuevo usuario en el sistema.
     *
     * @param request Datos del usuario a registrar.
     * @return Token de autenticación y token de actualización.
     */
    AuthTokenRes register(UserRegisterReq request);

    /**
     * Refresca el token de autenticación utilizando un token de actualización.
     *
     * @param request  Solicitud HTTP.
     * @param response Respuesta HTTP.
     * @throws IOException En caso de errores de lectura o escritura.
     */
    void refreshToken(HttpServletRequest request, HttpServletResponse response)
            throws StreamWriteException, DatabindException, IOException;
}
