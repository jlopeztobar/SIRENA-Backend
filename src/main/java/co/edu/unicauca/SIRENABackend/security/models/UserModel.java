package co.edu.unicauca.SIRENABackend.security.models;

import java.util.Collection;
import java.util.List;

import org.hibernate.type.NumericBooleanConverter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa un modelo de usuario en la aplicación.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@Schema(description = "Entidad que representa el usuario en la aplicación")
public class UserModel implements UserDetails {

    /**
     * Identificador único del usuario.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_int_id", unique = true)
    @Schema(description = "Identificador único del usuario", example = "1")
    private Integer id;

    /**
     * Nombre de usuario del usuario.
     */
    @Column(name = "usr_name", nullable = false, length = 20, unique = true)
    @Schema(description = "Nombre de usuario del usuario", example = "john_doe")
    private String username;

    /**
     * Primer nombre del usuario.
     */
    @Column(name = "usr_firstname", nullable = false, length = 20)
    @Schema(description = "Primer nombre del usuario", example = "John")
    private String firstName;

    /**
     * Apellido del usuario.
     */
    @Column(name = "usr_lastname", nullable = false, length = 20)
    @Schema(description = "Apellido del usuario", example = "Doe")
    private String lastName;

    /**
     * Correo electrónico del usuario.
     */
    @Column(name = "usr_email", nullable = false, length = 50, unique = true)
    @Email
    @Schema(description = "Correo electrónico del usuario", example = "john.doe@example.com")
    private String email;

    /**
     * Contraseña del usuario.
     */
    @Column(name = "usr_password", nullable = false, length = 512)
    @Schema(description = "Contraseña del usuario (hash)", example = "hashedPassword")
    private String password;

    /**
     * Estado del usuario (activo o inactivo).
     */
    @Default
    @Convert(converter = NumericBooleanConverter.class)
    @Column(name = "usr_status", nullable = false)
    @Schema(description = "Estado del usuario (activo o inactivo)", defaultValue = "true")
    protected Boolean status = true;

    /**
     * Rol del usuario representado por la clase `RoleModel`.
     */
    @ManyToOne
    @JoinColumn(name = "usr_role", nullable = false)
    @Schema(description = "Rol del usuario", implementation = RoleModel.class)
    private RoleModel role;

    /**
     * Lista de tokens asociados al usuario.
     */
    @OneToMany(mappedBy = "user")
    @Column(name = "usr_tokens")
    @Schema(description = "Lista de tokens asociados al usuario", implementation = TokenModel.class)
    private List<TokenModel> tokens;

    /**
     * Establece el estado del usuario.
     *
     * @param prmStatus Estado del usuario.
     */

    public void setStatus(Boolean prmStatus) {
        this.status = prmStatus;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.getName().name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
