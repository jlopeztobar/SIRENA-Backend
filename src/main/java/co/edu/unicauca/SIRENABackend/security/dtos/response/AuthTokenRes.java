package co.edu.unicauca.SIRENABackend.security.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO (Data Transfer Object) que representa la respuesta de la autenticación, incluyendo los tokens de acceso y de refresco.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthTokenRes {

    /**
     * Token de acceso.
     */
    @JsonProperty("acces_token")
    private String accesToken;

    /**
     * Token de refresco.
     */
    @JsonProperty("refresh_token")
    private String refreshToken;
}
