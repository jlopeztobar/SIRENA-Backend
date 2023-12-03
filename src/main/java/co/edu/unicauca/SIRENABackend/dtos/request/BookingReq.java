package co.edu.unicauca.SIRENABackend.dtos.request;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import co.edu.unicauca.SIRENABackend.common.enums.BookingStateTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) que representa la solicitud para crear o
 * actualizar una reserva.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingReq {

    /**
     * Fecha y hora en que se realizó la solicitud de reserva.
     */
    @JsonProperty("rsv_fecha_solicitud")
    @JsonIgnore
    private LocalDateTime fechaSolicitud;

    /**
     * Fecha y hora de inicio de la reserva.
     */
    @JsonProperty("rsv_fecha_reserva_inicio")
    private LocalDateTime fechaReservaInicio;

    /**
     * Fecha y hora de finalización de la reserva.
     */
    @JsonProperty("rsv_hora_fin")
    private LocalDateTime horaFin;

    /**
     * Número de estudiantes para los que se realiza la reserva.
     */
    @JsonProperty("rsv_num_estudiantes")
    private Integer numEstudiantes;

    /**
     * Estado de la reserva (Pendiente, Aceptada, Rechazada).
     */
    @JsonProperty("rsv_estado")
    private BookingStateTypeEnum estado;

    /**
     * Detalles adicionales de la reserva.
     */
    @JsonProperty("rsv_detalles")
    private String detalles;

    /**
     * Identificador único de la incidencia asociada a la reserva (opcional).
     */
    @JsonProperty("rsv_incidencia_id")
    private Integer incidenciasID;

    /**
     * Identificador único del aula asociada a la reserva.
     */
    @JsonProperty("rsv_cls_id")
    private Integer classroomID;

    /**
     * Identificador único del usuario que realiza la reserva.
     */
    @JsonProperty("rsv_usr_id")
    private Integer userID;

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
