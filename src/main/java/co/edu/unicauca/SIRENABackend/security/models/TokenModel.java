package co.edu.unicauca.SIRENABackend.security.models;

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

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tokens")
public class TokenModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tok_int_id", unique = true)
    private Integer id;

    @Column(name = "tok_token", nullable = false)
    private String token;

    @Enumerated(EnumType.STRING)
    @Column(name = "tok_type", nullable = false)
    private TokenTypeEnum tokenType;

    @Convert(converter = NumericBooleanConverter.class)
    @Column(name = "tok_expired", nullable = false)
    private boolean expired;

    @Convert(converter = NumericBooleanConverter.class)
    @Column(name = "tok_revoked", nullable = false)
    private boolean revoked;

    @ManyToOne
    @JoinColumn(name = "usr_int_id")
    private UserModel user;
}
