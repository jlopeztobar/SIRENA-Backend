package co.edu.unicauca.SIRENABackend.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Modelo de entidad que representa un edificio en el sistema.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "buildings")
@Schema(description = "Entidad que representa un edificio en el sistema")
public class BuildingModel {

    /**
     * Identificador único del edificio.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bld_int_id", unique = true)
    @Schema(description = "Identificador único del edificio", example = "1")
    private Integer id;

    /**
     * Nombre del edificio.
     */
    @Column(name = "bld_name", nullable = false, length = 40)
    @Schema(description = "Nombre del edificio")
    private String name;
}
