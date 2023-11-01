package co.edu.unicauca.SIRENABackend.security.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthTokenRes {

    @JsonProperty("acces_token")
    private String accesToken;
    
    @JsonProperty("refresh_token")
    private String refreshToken;
}
