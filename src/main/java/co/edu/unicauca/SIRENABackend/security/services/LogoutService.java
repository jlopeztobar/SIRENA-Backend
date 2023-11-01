package co.edu.unicauca.SIRENABackend.security.services;

import org.springframework.security.core.Authentication;

import co.edu.unicauca.SIRENABackend.security.models.UserModel;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface LogoutService {
    void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication);

    void revokeAllUserTokens(UserModel prmUser);
}
