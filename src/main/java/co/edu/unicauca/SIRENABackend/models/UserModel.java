package co.edu.unicauca.SIRENABackend.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa un modelo de usuarios en la aplicación.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserModel implements UserDetails{

    /**
     * Identificador único del usuario.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_int_id", unique = true)
    private Integer id;

    /**
     * Nombre del usuario.
     */
    //@Column(name = "usr_name", nullable = false, length = 20)
    @Column(name = "username", nullable = false, length = 70, unique = true)
    private String username;

    /**
     * Primer nombre del usuario.
     *
     */
    @Column(name = "usr_firstname", nullable = false, length = 20)
    private String firstName;

    /**
     * Apellido del usuario.
     */
    @Column(name = "usr_lastname", nullable = false, length = 20)
    private String lastName;

    /**
     * Email del usuario.
     */
    @Column(name = "user_email", nullable = false, length = 70, unique = true)
    private String email;

    /**
     * contraseña del usuario.
     */
    @Column(name = "password", nullable = false, length = 512)
    private String password;

    /**
     * Rol del usuario.
     */
    @ManyToOne
    @JoinColumn(name = "usr_role")
    private RoleModel role;

    /**
     * Salon asociado al usuario.
     */
    @Builder.Default
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
        name = "users_classrooms",
        joinColumns = @JoinColumn(name = "usr_int_id"),
        inverseJoinColumns = @JoinColumn(name = "cls_int_id")
        )
    private Set<ClassroomModel> classroom_assigned = new HashSet<>();



    /*
     * Metodos de UserDetails
     */

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((role.getName())));
    }

    @Override
    public String getUsername() {
        return username;
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
