package co.edu.unicauca.SIRENABackend.Auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    
    /**
     * Maneja la solicitud de inicio de sesión.
     *
     * @param request La solicitud de inicio de sesión.
     * @return ResponseEntity que contiene la respuesta de autenticación.
     */
    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request)
    {
        return ResponseEntity.ok(authService.login(request));
    }

    /**
     * Maneja la solicitud de registro.
     *
     * @param request La solicitud de registro.
     * @return ResponseEntity que contiene la respuesta de autenticación.
     */
    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request)
    {
        return ResponseEntity.ok(authService.register(request));
    }
}
