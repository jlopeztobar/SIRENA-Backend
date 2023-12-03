package co.edu.unicauca.SIRENABackend.dtos.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import co.edu.unicauca.SIRENABackend.common.enums.BookingStateTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) que representa la respuesta de una reserva.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingRes {

    /**
     * Identificador único de la reserva.
     */
    @JsonProperty("rsv_id")
    private Integer id;

    /**
     * Fecha en la que se realizó la solicitud de la reserva.
     */
    @JsonProperty("rsv_fecha_solicitud")
    private LocalDateTime fechaSolicitud;

    /**
     * Fecha de inicio de la reserva.
     */
    @JsonProperty("rsv_fecha_reserva_inicio")
    private LocalDateTime fechaReservaInicio;

    /**
     * Hora de finalización de la reserva.
     */
    @JsonProperty("rsv_hora_fin")
    private LocalDateTime horaFin;

    /**
     * Número de estudiantes para los que se realiza la reserva.
     */
    @JsonProperty("rsv_num_estudiantes")
    private Integer numEstudiantes;

    /**
     * Estado actual de la reserva (pendiente, aceptada, rechazada, etc.).
     */
    @JsonProperty("rsv_estado")
    private BookingStateTypeEnum estado;

    /**
     * Detalles adicionales o comentarios asociados a la reserva.
     */
    @JsonProperty("rsv_detalles")
    private String detalles;

    /**
     * Incidencias asociadas a la reserva.
     */
    @JsonProperty("rsv_incidencia")
    private IncidenceRes incidencias;

    /**
     * Identificador único del salón de clases reservado.
     */
    @JsonProperty("rsv_cls_id")
    private Integer classroomID;

    /**
     * Identificador único del usuario que realizó la reserva.
     */
    @JsonProperty("rsv_usr")
    private UserRes user;

    /**
     * Identificador único de la facultad que realiza la reserva.
     */
    @JsonProperty("rsv_faculty_id")
    private Integer facultyId;

    /**
     * Identificador único de programa que realiza la reserva.
     */
    @JsonProperty("rsv_program_id")
    private Integer programId;
}
