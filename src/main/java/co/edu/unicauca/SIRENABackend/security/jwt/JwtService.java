package co.edu.unicauca.SIRENABackend.security.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import co.edu.unicauca.SIRENABackend.security.common.enums.TokenTypeEnum;
import co.edu.unicauca.SIRENABackend.security.models.TokenModel;
import co.edu.unicauca.SIRENABackend.security.models.UserModel;
import co.edu.unicauca.SIRENABackend.security.repositories.ITokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

/**
 * Servicio para la gestión de tokens JWT.
 */
@Service
public class JwtService {

    @Autowired
    private ITokenRepository tokenRepository;

    @Value("${jwt.secret-key}")
    private String secretKey;
    @Value("${jwt.expitation}")
    private long jwtExpiration;
    @Value("${jwt.refresh-token.expitation}")
    private long refreshExpiration;

    /**
     * Genera un token JWT para un usuario.
     *
     * @param user     Detalles del usuario.
     * @param prmUserID Identificador del usuario.
     * @return Token JWT.
     */
    public String getToken(UserDetails user, Integer prmUserID) {
        return getToken(new HashMap<>(), user, prmUserID);
    }

    private String getToken(Map<String, Object> extraClaims, UserDetails userDetails, Integer prmUserID) {
        return buildToken(extraClaims, userDetails, prmUserID, jwtExpiration);
    }

    /**
     * Genera un token JWT de actualización (refresh token) para un usuario.
     *
     * @param user     Detalles del usuario.
     * @param prmUserID Identificador del usuario.
     * @return Token JWT de actualización.
     */
    public String getRefreshToken(UserDetails user, Integer prmUserID) {
        return buildToken(new HashMap<>(), user, prmUserID, refreshExpiration);
    }

    /**
     * Construye un token JWT con información adicional y configuraciones específicas.
     *
     * @param extraClaims   Mapa de claims adicionales que se agregarán al token.
     * @param userDetails   Detalles del usuario para los cuales se está construyendo el token.
     * @param prmUserID     Identificador único del usuario.
     * @param prmExpiration Tiempo de expiración del token en milisegundos.
     * @return Token JWT construido.
     */
    private String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, Integer prmUserID,
            long prmExpiration) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .claim("subId", prmUserID)
                .claim("role", userDetails.getAuthorities().stream().findFirst().get().getAuthority())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + prmExpiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Obtiene el nombre de usuario desde un token JWT.
     *
     * @param token Token JWT.
     * @return Nombre de usuario.
     */
    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    /**
     * Obtiene una reclamación (claim) específica de un token JWT.
     *
     * @param token           Token JWT.
     * @param claimsResolver  Función para resolver la reclamación.
     * @param <T>             Tipo de la reclamación.
     * @return Reclamación específica del token.
     */
    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Obtiene todos los reclamos (claims) del token.
     *
     * @param token El token del cual se obtendrán los reclamos.
     * @return Objeto Claims que contiene los reclamos del token.
     */
    private Claims getAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Obtiene la clave de firma (signing key) utilizada para firmar y verificar tokens.
     *
     * @return Clave de firma.
     */
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Verifica si un token JWT es válido para un usuario.
     *
     * @param token       Token JWT.
     * @param userDetails Detalles del usuario.
     * @return `true` si el token es válido, `false` en caso contrario.
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Obtiene la fecha de expiración del token.
     *
     * @param token El token del cual se obtendrá la fecha de expiración.
     * @return Fecha de expiración del token.
     */
    private Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }

    /**
     * Verifica si el token ha expirado.
     *
     * @param token El token que se verificará.
     * @return `true` si el token ha expirado, de lo contrario, `false`.
     */
    private boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }

    /**
     * Guarda el token de un usuario en la base de datos.
     *
     * @param prmUser      Usuario al que pertenece el token.
     * @param prmJwtToken  Token JWT.
     */
    public void saveUserToken(UserModel prmUser, String prmJwtToken) {
        var token = TokenModel.builder()
                .user(prmUser)
                .token(prmJwtToken)
                .tokenType(TokenTypeEnum.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    /**
     * Revoca todos los tokens válidos de un usuario.
     *
     * @param prmUser Usuario cuyos tokens se revocarán.
     */
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

    /**
     * Busca un token en la base de datos por su valor.
     *
     * @param refreshToken Valor del token.
     * @return Token encontrado (si existe).
     */
    public Optional<TokenModel> findByToken(String refreshToken) {
        return tokenRepository.findByToken(refreshToken);
    }
}
