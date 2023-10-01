package co.edu.unicauca.SIRENABackend.models;

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
 * Clase que representa un modelo del tipo de aula (classroomType) en la aplicación.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "classroom_types")
public class ClassroomTypeModel {

    /**
     * Identificador único del tipo de aula.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cls_type_int_id", unique = true)
    private Integer id;

    /**
     * Nombre del tipo de aula.
     */
    @Column(name = "cls_type_name", nullable = false, length = 20)
    private String name;
    
}