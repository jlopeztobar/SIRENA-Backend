package co.edu.unicauca.SIRENABackend.models;

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
 * Clase que representa un modelo de insidencias en la aplicación.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "incidences")
public class IncidenceModel {

    /**
     * Identificador único de la insidencia
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ins_int_id", unique = true)
    private Integer id;

    /**
     * Nombre del insidencia.
     */
    @Column(name = "ins_name", nullable = false, length = 20)
    private String name;

    /**
     * Codigo de insidencia.
     */
    //Es la mismas que reserva == rsv_codigo_insidencias
    @Column(name = "ins_key", nullable = false, length = 20)
    private String key;

    /**
     * Nombre del profesor de la insidencia.
     */
    //Es la misma que user ID == rsv_usr_int_id
    @ManyToOne
    @JoinColumn(name = "ins_teacher_name", nullable = false)
    private UserModel teacherName;

    /**
     * Reserva con insidencia.
     */
    @ManyToOne
    @JoinColumn(name = "ins_insidencias", nullable = false)
    private BookingModel insidencias;

    /**
     * tipo de insidencia.
     */
    @ManyToOne
    @JoinColumn(name = "ins_type", nullable = false)
    private IncidenceTypeModel insidenciaType;
}
