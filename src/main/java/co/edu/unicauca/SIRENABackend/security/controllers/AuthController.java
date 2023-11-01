package co.edu.unicauca.SIRENABackend.security.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;

import co.edu.unicauca.SIRENABackend.security.dtos.request.UserLoginReq;
import co.edu.unicauca.SIRENABackend.security.dtos.request.UserRegisterReq;
import co.edu.unicauca.SIRENABackend.security.dtos.response.AuthTokenRes;
import co.edu.unicauca.SIRENABackend.security.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authenticacion", description = "Endpoints para autenticación")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Operation(description = "Endpoint para iniciar sesión todos los usuarios", summary = "Iniciar sesión", responses = {
            @ApiResponse(responseCode = "200", description = "Autenticación exitosa", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Credenciales inválidas", content = @Content(mediaType = "application/json"))
    })
    @PostMapping(value = "/login")
    public ResponseEntity<AuthTokenRes> login(@RequestBody UserLoginReq request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping(value = "/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response)
            throws StreamWriteException, DatabindException, IOException {
        authService.refreshToken(request, response);
    }

    /**
     * Maneja la solicitud de registro.
     *
     * @param request La solicitud de registro.
     * @return ResponseEntity que contiene la respuesta de autenticación.
     */
    @PostMapping(value = "/register")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<AuthTokenRes> register(@RequestBody UserRegisterReq request) {
        return ResponseEntity.ok(authService.register(request));
    }
}
