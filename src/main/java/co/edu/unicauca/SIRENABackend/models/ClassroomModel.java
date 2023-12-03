package co.edu.unicauca.SIRENABackend.models;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
 * Modelo de entidad que representa un salón de clases en el sistema.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "classrooms")
@Schema(description = "Entidad que representa un salón de clases en el sistema")
public class ClassroomModel implements Serializable {

    /**
     * Identificador único del salón de clases.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cls_int_id", unique = true)
    @Schema(description = "Identificador único del salón de clases", example = "1")
    private Integer id;

    /**
     * Nombre del salón de clases.
     */
    @Column(name = "cls_name", nullable = false, length = 40, unique = true)
    @Schema(description = "Nombre del salón de clases")
    private String name;

    /**
     * Capacidad del salón de clases.
     */
    @Column(name = "cls_capacity", nullable = false)
    @Schema(description = "Capacidad del salón de clases")
    private Integer capacity;

    /**
     * Estado del salón de clases.
     */
    @Column(name = "cls_state", nullable = false, length = 100)
    @Schema(description = "Estado del salón de clases")
    private String state;

    /**
     * Edificio al que pertenece el salón de clases.
     */
    @ManyToOne
    @JoinColumn(name = "cls_building", nullable = false)
    @Schema(description = "Edificio al que pertenece el salón de clases")
    private BuildingModel building;

    /**
     * Tipo de salón de clases.
     */
    @ManyToOne
    @JoinColumn(name = "cls_type", nullable = false)
    @Schema(description = "Tipo de salón de clases")
    private ClassroomTypeModel classroomType;

}
