package co.edu.unicauca.SIRENABackend.security.controllers;

import java.io.IOException;
import java.rmi.ServerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;

import co.edu.unicauca.SIRENABackend.security.dtos.request.UserLoginReq;
import co.edu.unicauca.SIRENABackend.security.dtos.request.UserRegisterReq;
import co.edu.unicauca.SIRENABackend.security.dtos.response.AuthTokenRes;
import co.edu.unicauca.SIRENABackend.security.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

/**
 * Controlador que maneja los endpoints relacionados con la autenticación y autorización de usuarios.
 * Proporciona funcionalidades para iniciar sesión, refrescar tokens y registrar nuevos usuarios.
 */
@RestController
@RequestMapping("/auth")
@Tag(name = "Authenticacion", description = "Endpoints para autenticación")
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * Inicia sesión para un usuario existente y devuelve un token de acceso.
     *
     * @param request Objeto de solicitud que contiene las credenciales del usuario.
     * @return ResponseEntity con el token de acceso en caso de autenticación exitosa, o un ResponseEntity con código 400 si las credenciales son inválidas.
     */
    @Operation(summary = "Iniciar sesión", description = "Autenticar a los usuarios y obtener un token de acceso.", responses = {
            @ApiResponse(responseCode = "200", description = "Autenticación exitosa", content = @Content(schema = @Schema(implementation = AuthTokenRes.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Credenciales inválidas", content = @Content(mediaType = "application/json"))
    })
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/login")
    public ResponseEntity<AuthTokenRes> login(@Valid @RequestBody UserLoginReq request) {
        AuthTokenRes authToken = authService.login(request);
        if (authToken != null) {
            return ResponseEntity.ok(authToken);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Refresca el token de acceso existente.
     *
     * @param request  Objeto de solicitud HTTP.
     * @param response Objeto de respuesta HTTP.
     * @return ResponseEntity con código 200 si el token se refresca con éxito, o un ResponseEntity con código 401 si la solicitud no está autorizada, o con código 500 si hay un error interno del servidor.
     * @throws StreamWriteException Excepción de escritura de flujo.
     * @throws DatabindException     Excepción de enlace de datos.
     * @throws IOException           Excepción de E/S.
     */
    @Operation(summary = "Refrescar token de acceso", description = "Refrescar el token de acceso existente.", responses = {
            @ApiResponse(responseCode = "200", description = "Token refrescado exitosamente"),
            @ApiResponse(responseCode = "401", description = "No autorizado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/refresh-token")
    public ResponseEntity<Void> refreshToken(HttpServletRequest request, HttpServletResponse response)
            throws StreamWriteException, DatabindException, IOException {
        try {
            authService.refreshToken(request, response);
            return ResponseEntity.ok().build();
        } catch (Unauthorized ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (ServerException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Registra un nuevo usuario y devuelve un token de acceso.
     *
     * @param request Objeto de solicitud que contiene la información del nuevo usuario.
     * @return ResponseEntity con el token de acceso en caso de registro exitoso, o un ResponseEntity con código 400 si la solicitud es incorrecta.
     */
    @Operation(summary = "Registrar un nuevo usuario", description = "Registrar un nuevo usuario y obtener un token de acceso.", responses = {
            @ApiResponse(responseCode = "200", description = "Registro exitoso", content = @Content(schema = @Schema(implementation = AuthTokenRes.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/register")
    public ResponseEntity<AuthTokenRes> register(@Valid @RequestBody UserRegisterReq request) {
        AuthTokenRes authToken = authService.register(request);
        if (authToken != null) {
            return ResponseEntity.ok(authToken);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
