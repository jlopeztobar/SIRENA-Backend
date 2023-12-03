package co.edu.unicauca.SIRENABackend.security.models;

import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.type.NumericBooleanConverter;

import co.edu.unicauca.SIRENABackend.security.common.enums.TokenTypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa un modelo de token en la aplicación.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tokens")
@Schema(description = "Entidad que representa el token en la aplicación")
public class TokenModel {

    /**
     * Identificador único del token.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tok_int_id", unique = true)
    @Schema(description = "Identificador único del token", example = "1")
    private Integer id;

    /**
     * Valor del token.
     */
    @Column(name = "tok_token", nullable = false)
    @Schema(description = "Valor del token")
    private String token;

    /**
     * Tipo de token representado por la enumeración `TokenTypeEnum`.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "tok_type", nullable = false)
    @Schema(description = "Tipo de token representado por la enumeración `TokenTypeEnum`", example = "BEARER")
    private TokenTypeEnum tokenType;

    /**
     * Indica si el token ha caducado.
     */
    @Convert(converter = NumericBooleanConverter.class)
    @Column(name = "tok_expired", nullable = false)
    @Schema(description = "Indica si el token ha caducado.")
    private boolean expired;

    /**
     * Indica si el token ha sido revocado.
     */
    @Convert(converter = NumericBooleanConverter.class)
    @Column(name = "tok_revoked", nullable = false)
    @Schema(description = "Indica si el token ha sido revocado.")
    private boolean revoked;

    /**
     * Usuario asociado al token.
     */
    @ManyToOne
    @JoinColumn(name = "usr_int_id")
    @Schema(description = "Id de usuario asociado al token", example = "1")
    private UserModel user;
}
