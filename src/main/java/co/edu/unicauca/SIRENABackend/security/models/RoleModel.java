package co.edu.unicauca.SIRENABackend.security.models;

import co.edu.unicauca.SIRENABackend.security.common.enums.RoleEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa un modelo de rol en la aplicación.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class RoleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rol_int_id", unique = true, nullable = false)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol_name", unique = true, nullable = false)
    private RoleEnum name;
}
