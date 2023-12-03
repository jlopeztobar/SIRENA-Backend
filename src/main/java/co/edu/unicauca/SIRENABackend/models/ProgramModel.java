package co.edu.unicauca.SIRENABackend.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "programs")
@Schema(description = "Entidad que representa un programa en el sistema")
public class ProgramModel {
    /**
     * Identificador único del programa.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prgm_int_id", unique = true)
    @Schema(description = "Identificador único del programa", example = "1")
    private Integer id;

    /**
     * Nombre del programa.
     */
    @Column(name = "prgm_name", nullable = false, length = 40)
    @Schema(description = "Nombre del programa")
    private String name;

    /**
     * Edificio al que pertenece lel programa.
     */
    @ManyToOne
    @JoinColumn(name = "prgm_faculty")
    @Schema(description = "Edificio al que pertenece el programa.", example = "2")
    private FacultyModel faculty;
}
