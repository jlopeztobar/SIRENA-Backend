package co.edu.unicauca.SIRENABackend.security.models;

import co.edu.unicauca.SIRENABackend.security.common.enums.RoleEnum;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Entidad que representa un rol en la aplicación")
public class RoleModel {

    /**
     * Identificador único del rol.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rol_int_id", unique = true, nullable = false)
    @Schema(description = "Identificador único del rol", example = "1")
    private Integer id;

    /**
     * Nombre del rol.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "rol_name", unique = true, nullable = false)
    @Schema(description = "Nombre del rol", example = "ADMIN")
    private RoleEnum name;
}
