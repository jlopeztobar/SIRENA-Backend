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

import co.edu.unicauca.SIRENABackend.security.common.enums.TokenTypeEnum;
import co.edu.unicauca.SIRENABackend.security.dtos.request.UserLoginReq;
import co.edu.unicauca.SIRENABackend.security.dtos.request.UserRegisterReq;
import co.edu.unicauca.SIRENABackend.security.dtos.response.AuthTokenRes;
import co.edu.unicauca.SIRENABackend.security.jwt.JwtService;
import co.edu.unicauca.SIRENABackend.security.models.RoleModel;
import co.edu.unicauca.SIRENABackend.security.models.TokenModel;
import co.edu.unicauca.SIRENABackend.security.models.UserModel;
import co.edu.unicauca.SIRENABackend.security.repositories.IRoleRepository;
import co.edu.unicauca.SIRENABackend.security.repositories.ITokenRepository;
import co.edu.unicauca.SIRENABackend.security.repositories.IUserRepository;
import co.edu.unicauca.SIRENABackend.security.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final ITokenRepository tokenRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthTokenRes login(UserLoginReq request) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),
                        request.getPassword()));
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow();

        var jwtToken = jwtService.getToken(user);
        var refreshToken = jwtService.getRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        saveUserToken(user, refreshToken);
        return AuthTokenRes.builder()
                .accesToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public AuthTokenRes register(UserRegisterReq request) throws RuntimeException {
        // if (userRepository.existsByUsername(request.getUsr_name())) {
        // throw new RuntimeException("Username already exists");
        // }
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
        var jwtToken = jwtService.getToken(user);
        var refreshToken = jwtService.getRefreshToken(user);

        saveUserToken(savedUser, jwtToken);
        saveUserToken(savedUser, refreshToken);

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
            var isTokenValid = tokenRepository.findByToken(refreshToken)
                    .map(token -> !token.isExpired() && !token.isRevoked())
                    .orElse(false);
            if (jwtService.isTokenValid(refreshToken, user) && isTokenValid) {
                var accesToken = jwtService.getRefreshToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accesToken);
                var authResponse = AuthTokenRes.builder()
                        .accesToken(accesToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

    private void revokeAllUserTokens(UserModel prmUser) {
        var validUserToken = tokenRepository.findAllValidTokensByUser(prmUser.getId());
        if (validUserToken.isEmpty())
            return;
        validUserToken.forEach(Token -> {
            Token.setExpired(true);
            Token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserToken);
    }

    private void saveUserToken(UserModel prmUser, String prmJwtToken) {
        var token = TokenModel.builder()
                .user(prmUser)
                .token(prmJwtToken)
                .tokenType(TokenTypeEnum.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }
}
