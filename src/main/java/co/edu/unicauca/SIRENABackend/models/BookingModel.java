package co.edu.unicauca.SIRENABackend.models;

import java.time.LocalDateTime;

import co.edu.unicauca.SIRENABackend.common.enums.BookingStateTypeEnum;
import co.edu.unicauca.SIRENABackend.security.models.UserModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Modelo de entidad que representa una reserva (booking) en el sistema.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "booking")
@Schema(description = "Entidad que representa una reserva en el sistema")
public class BookingModel {

    /**
     * Identificador único de la reserva.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rsv_int_id")
    @Schema(description = "Identificador único de la reserva", example = "1")
    private Integer id;

    /**
     * Fecha y hora en que se realizó la solicitud de la reserva.
     */
    @Column(name = "rsv_fecha_solicitud", nullable = false)
    @Schema(description = "Fecha y hora en que se realizó la solicitud de la reserva")
    private LocalDateTime fechaSolicitud;

    /**
     * Fecha y hora de inicio de la reserva.
     */
    @Column(name = "rsv_fecha_reserva_inicio", nullable = false)
    @Schema(description = "Fecha y hora de inicio de la reserva")
    private LocalDateTime fechaReservaInicio;

    /**
     * Fecha y hora de finalización de la reserva.
     */
    @Column(name = "rsv_hora_fin", nullable = false)
    @Schema(description = "Fecha y hora de finalización de la reserva")
    private LocalDateTime horaFin;

    /**
     * Número de estudiantes para los que se realiza la reserva.
     */
    @Column(name = "rsv_num_estudiantes", nullable = true)
    @Schema(description = "Número de estudiantes para los que se realiza la reserva")
    private Integer numEstudiantes;

    /**
     * Estado actual de la reserva.
     */
    @Default
    @Enumerated(EnumType.STRING)
    @Column(name = "rsv_estado", nullable = false, length = 20)
    @Schema(description = "Estado actual de la reserva", example = "Pendiente")
    private BookingStateTypeEnum estado = BookingStateTypeEnum.Pendiente;

    /**
     * Detalles adicionales de la reserva.
     */
    @Column(name = "rsv_detalles", nullable = true)
    @Schema(description = "Detalles adicionales de la reserva")
    private String detalles;

    /**
     * Incidencia asociada a la reserva, si la hay.
     */
    @OneToOne
    @Default
    @JoinColumn(name = "rsv_incidencia")
    @Schema(description = "Incidencia asociada a la reserva, si la hay")
    private IncidenceModel incidencias = null;

    /**
     * Aula de clases asociada a la reserva.
     */
    @ManyToOne
    @JoinColumn(name = "rsv_cls_int_id", nullable = false)
    @Schema(description = "Aula de clases asociada a la reserva", implementation = ClassroomModel.class)
    private ClassroomModel classroom;

    /**
     * Usuario que realizó la reserva.
     */
    @ManyToOne
    @JoinColumn(name = "rsv_usr_int_id", nullable = false)
    @Schema(description = "Usuario que realizó la reserva", implementation = UserModel.class)
    private UserModel user;

    /**
     * Facultad que realizó la reserva.
     */
    @ManyToOne
    @JoinColumn(name = "rsv_faculty", nullable = false)
    @Schema(description = "Facultad que realizó la reserva", implementation = FacultyModel.class)
    private FacultyModel faculty;

    /**
     * Programa que realizó la reserva.
     */
    @ManyToOne
    @JoinColumn(name = "rsv_program", nullable = false)
    @Schema(description = "Programa que realizó la reserva", implementation = ProgramModel.class)
    private ProgramModel program;
}
