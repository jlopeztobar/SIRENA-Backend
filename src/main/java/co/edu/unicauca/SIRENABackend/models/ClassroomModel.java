package co.edu.unicauca.SIRENABackend.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa un modelo de aula (classroom) en la aplicación.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "classrooms")
public class ClassroomModel implements Serializable {

    /**
     * Identificador único de la aula.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cls_int_id", unique = true)
    private  Integer id;

    /**
     * Nombre del aula.
     */
    @Column(name = "cls_name", nullable = false, length = 20)
    private  String name;

    /**
     * Capacidad máxima de personas en el aula.
     */
    @Column(name = "cls_capacity", nullable = false, length = 20)
    private Integer capacity;

    /**
     * Estado actual del aula.
     */
    @Column(name = "cls_state", nullable = false, length = 20)
    private  String state;

    /**
     * Edificio al que pertenece el aula.
     */
    @Column(name = "cls_building", nullable = false, length = 20)
    private  String building;

    /**
     * Tipo de aula al que pertenece.
     */
    @ManyToOne
    @JoinColumn(name = "cls_type", nullable = false)
    private ClassroomTypeModel classroomType;

    /**
     * Lista de usuarios asignados a esta aula.
     */
    @Builder.Default
    @ManyToMany(mappedBy = "classroom_assigned")
    private Set<UserModel> userList = new HashSet<>(); 
}
