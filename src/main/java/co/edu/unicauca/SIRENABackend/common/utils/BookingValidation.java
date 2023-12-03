package co.edu.unicauca.SIRENABackend.common.utils;

import java.time.LocalDateTime;
import java.time.LocalTime;

import co.edu.unicauca.SIRENABackend.common.enums.BookingStateTypeEnum;
import co.edu.unicauca.SIRENABackend.dtos.request.BookingReq;

public class BookingValidation {

    public boolean validationObj(BookingReq bookingToValidate) {
        if (bookingToValidate.getClassroomID() == null) {
            System.out.println("El salon no pueden ser nulos");
            return false;
        }
        if (bookingToValidate.getFacultyId() == null) {
            System.out.println("La facultad no puede ser nula");
            return false;
        }
        // Verificar que la fecha de solicitud es anterior a la de inicio
        if (!bookingToValidate.getFechaSolicitud().isBefore(bookingToValidate.getFechaReservaInicio())) {
            System.out.println("La fecha de solicitud debe ser anterior a la de inicio");
            return false;
        }
        // Verificar que la fecha fin es posterior a la de inicio
        if (!bookingToValidate.getFechaReservaInicio().isBefore(bookingToValidate.getHoraFin())) {
            System.out.println("La fecha de inicio debe ser anterior a la de fin");
            return false;
        }
        // Verificar que la reserva esta en el rango
        if (!isTimeInRange(bookingToValidate.getFechaReservaInicio())
                || !isTimeInRange(bookingToValidate.getHoraFin())) {
            System.out.println("La reserva debe estar entre las 6am y las 11pm");
            return false;
        }

        // Verificar que el estado es valido
        boolean bandera = false;
        for (BookingStateTypeEnum BookingState : BookingStateTypeEnum.values()) {
            if (BookingState.equals(bookingToValidate.getEstado())) {
                bandera = true;
                break;
            }
        }
        if (!bandera) {
            System.out.println("El estado no" + bookingToValidate.getEstado().name() + " es valido");
            return false;
        }

        return true;
    }

    /**
     * Verifica si el tiempo de una fecha está en un rango específico (de 6:00 AM a
     * 11:00 PM).
     *
     * @param localDate Fecha y hora para verificar.
     * @return {@code true} si la hora está en el rango, {@code false} en caso
     *         contrario.
     */
    public boolean isTimeInRange(LocalDateTime localDate) {

        LocalTime localTime = localDate.toLocalTime();

        LocalTime startTime = LocalTime.of(6, 0);
        LocalTime endTime = LocalTime.of(23, 0);

        return !localTime.isBefore(startTime) && !localTime.isAfter(endTime);
    }
}
