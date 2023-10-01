package co.edu.unicauca.SIRENABackend.models;

import java.util.Date;

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
 * Clase que representa un modelo de las reservas en la aplicación.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "booking")
public class BookingModel {

    /**
     * Identificador único de la reserva.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rsv_int_id")
    private Integer id;

    /**
     * fecha de solicitud de la reserva
     */
    @Column(name = "rsv_fecha_solicitud", nullable = false)
    private Date fechaSolicitud;

    /**
     * fecha de la reserva.
     */
    @Column(name = "rsv_fecha_reserva_inicio", nullable = false)
    private Date fechaReservaInicio;

    /**
     * Hora de finalización de la reserva.
     */
    @Column(name = "rsv_hora_fin", nullable = false)
    private Date horaFin;
    /**
     * numero de estudiantes que asistiran.
     */
    @Column(name = "rsv_num_estudiantes", nullable = true)
    private int numEstudiantes;

    /**
     * Estado de la reserva.
     */
    @Column(name = "rsv_estado", nullable = false, length = 20)
    private String estado;

    /**
     * detalles de la reserva.
     */
    @Column(name = "rsv_detalles", nullable = true)
    private String detalles;

    /**
     * Codigo si se presenta alguna insidencia.
     */
    @Column(name = "rsv_codigo_insidencias", nullable = false, unique = true)
    private String codigoInsidencias;

    /**
     * aula que quieren servar.
     */
    @ManyToOne
    @JoinColumn(name = "rsv_cls_int_id", nullable = false)
    private ClassroomModel classroomID;

    /**
     * Usuario que realizo la reserva.
     */
    @ManyToOne
    @JoinColumn(name = "rsv_usr_int_id", nullable = false)
    private UserModel userID;
}

