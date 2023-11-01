package co.edu.unicauca.SIRENABackend.security.services;

import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;

import co.edu.unicauca.SIRENABackend.security.dtos.request.UserLoginReq;
import co.edu.unicauca.SIRENABackend.security.dtos.request.UserRegisterReq;
import co.edu.unicauca.SIRENABackend.security.dtos.response.AuthTokenRes;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {
    AuthTokenRes login(UserLoginReq request);

    AuthTokenRes register(UserRegisterReq request);

    void refreshToken(HttpServletRequest request, HttpServletResponse response)
            throws StreamWriteException, DatabindException, IOException;
}
