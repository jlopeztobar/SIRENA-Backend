package co.edu.unicauca.SIRENABackend.models;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad que representa la facultad en el sistema
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "faculties")
@Schema(description = "Entidad que representa la facultad en el sistema.")
public class FacultyModel {

    /**
     * Identificador único de la facultad.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fac_int_id", unique = true)
    @Schema(description = "Identificador único de la facultad.", example = "1")
    private Integer id;

    /**
     * Nombre de la facultad.
     */
    @Column(name = "fac_name", nullable = false, length = 40)
    @Schema(description = "Nombre de la facultad.")
    private String name;

    /**
     * Edificio al que pertenece la facultad.
     */
    @ManyToOne
    @JoinColumn(name = "building_id")
    @Schema(description = "Edificio al que pertenece la facultad.", example = "2")
    private BuildingModel building;
}

