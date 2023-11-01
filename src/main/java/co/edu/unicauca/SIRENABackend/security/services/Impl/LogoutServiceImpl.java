package co.edu.unicauca.SIRENABackend.security.services.Impl;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import co.edu.unicauca.SIRENABackend.security.jwt.JwtService;
import co.edu.unicauca.SIRENABackend.security.models.UserModel;
import co.edu.unicauca.SIRENABackend.security.repositories.ITokenRepository;
import co.edu.unicauca.SIRENABackend.security.repositories.IUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogoutServiceImpl implements LogoutHandler{

    private final ITokenRepository tokenRepository;
    private final IUserRepository userRepository;
    private final JwtService jwtService;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String jwt;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        jwt = authHeader.substring(7);

        String user = jwtService.getUsernameFromToken(jwt);
        var userFound = userRepository.findByUsername(user).orElseThrow();
        revokeAllUserTokens(userFound);
    }

    public void revokeAllUserTokens(UserModel prmUser) {
        var validUserToken = tokenRepository.findAllValidTokensByUser(prmUser.getId());
        if (validUserToken.isEmpty())
            return;
        validUserToken.forEach(Token -> {
            Token.setExpired(true);
            Token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserToken);
    }

}
