package co.edu.unicauca.SIRENABackend.security.services.Impl;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.edu.unicauca.SIRENABackend.security.dtos.request.UserLoginReq;
import co.edu.unicauca.SIRENABackend.security.dtos.request.UserRegisterReq;
import co.edu.unicauca.SIRENABackend.security.dtos.response.AuthTokenRes;
import co.edu.unicauca.SIRENABackend.security.jwt.JwtService;
import co.edu.unicauca.SIRENABackend.security.models.RoleModel;
import co.edu.unicauca.SIRENABackend.security.models.UserModel;
import co.edu.unicauca.SIRENABackend.security.repositories.IRoleRepository;
import co.edu.unicauca.SIRENABackend.security.repositories.IUserRepository;
import co.edu.unicauca.SIRENABackend.security.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

/**
 * proporciona la lógica para la autenticación y registro de usuarios.
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthTokenRes login(UserLoginReq request) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),
                        request.getPassword()));
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        if (user.getStatus() == false) {
            throw new RuntimeException("User is inactive");
        }

        var jwtToken = jwtService.getToken(user, user.getId());
        var refreshToken = jwtService.getRefreshToken(user, user.getId());
        jwtService.revokeAllUserTokens(user);
        jwtService.saveUserToken(user, jwtToken);
        jwtService.saveUserToken(user, refreshToken);
        return AuthTokenRes.builder()
                .accesToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public AuthTokenRes register(UserRegisterReq request) throws RuntimeException {
        if (userRepository.existsByUsername(request.getUsr_name())) {
            return null;
        }
        if (userRepository.existsByEmail(request.getUsr_email())) {
            return null;
        }

        RoleModel role_insert = roleRepository.findByName(request.getUsr_role()).orElseThrow();
        UserModel user = UserModel.builder()
                .role(role_insert)
                .firstName(request.getUsr_firstname())
                .lastName(request.getUsr_lastname())
                .username(request.getUsr_name())
                .password(passwordEncoder.encode(request.getUsr_password()))
                .email(request.getUsr_email())
                .build();

        var savedUser = userRepository.save(user);
        var jwtToken = jwtService.getToken(user, savedUser.getId());
        var refreshToken = jwtService.getRefreshToken(user, savedUser.getId());

        jwtService.saveUserToken(savedUser, jwtToken);
        jwtService.saveUserToken(savedUser, refreshToken);

        return AuthTokenRes.builder()
                .accesToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response)
            throws StreamWriteException, DatabindException, IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String username;
        final String refreshToken;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }

        refreshToken = authHeader.substring(7);

        username = jwtService.getUsernameFromToken(refreshToken);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            var user = this.userRepository.findByUsername(username).orElseThrow();
            var isTokenValid = jwtService.findByToken(refreshToken)
                    .map(token -> !token.isExpired() && !token.isRevoked())
                    .orElse(false);
            if (jwtService.isTokenValid(refreshToken, user) && isTokenValid) {
                var accesToken = jwtService.getRefreshToken(user, user.getId());
                jwtService.revokeAllUserTokens(user);
                jwtService.saveUserToken(user, accesToken);
                var authResponse = AuthTokenRes.builder()
                        .accesToken(accesToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}
