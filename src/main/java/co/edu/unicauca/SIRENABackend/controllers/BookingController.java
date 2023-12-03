package co.edu.unicauca.SIRENABackend.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.SIRENABackend.common.utils.BookingValidation;
import co.edu.unicauca.SIRENABackend.dtos.request.BookingReq;
import co.edu.unicauca.SIRENABackend.dtos.response.BookingRes;
import co.edu.unicauca.SIRENABackend.security.dtos.response.UserRegisterRes;
import co.edu.unicauca.SIRENABackend.services.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * Controlador REST que maneja las operaciones relacionadas con las reservas
 * (bookings).
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/bookings")
@Tag(name = "Booking controller", description = "Endpoints para las reservas")
public class BookingController extends BookingValidation {

    private final BookingService bookingService;

    /**
     * Crea una nueva booking.
     *
     * @param bookingModel El objeto BookingModel que se desea crear y guardar.
     * @return Una respuesta HTTP con el objeto BookingModel creado y el código de
     *         estado 201 (CREATED).
     */
    @Operation(summary = "Registra una reserva", description = "crea una reserva en el sistema.", responses = {
            @ApiResponse(responseCode = "200", description = "reservas creada exitosamente", content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserRegisterRes.class)), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "No se creo la reservas", content = @Content(mediaType = "application/json"))
    })
    @PostMapping()
    public ResponseEntity<String> crearBooking(@RequestBody BookingReq bookingModel) {
        bookingModel.setFechaSolicitud(LocalDateTime.now());
        return this.bookingService.crearBooking(bookingModel);
    }

    /**
     * Obtiene todas las bookings almacenadas en la base de datos.
     *
     * @return Una respuesta HTTP con una lista de objetos BookingModel y el código
     *         de estado 200 (OK).
     */
    @Operation(summary = "Obtener todas las reservas", description = "Obtiene una lista de todas las reservas registrados en el sistema.", responses = {
            @ApiResponse(responseCode = "200", description = "Reservas obtenidos exitosamente", content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserRegisterRes.class)), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "No se encontraron reservas", content = @Content(mediaType = "application/json"))
    })
    @GetMapping()
    public ResponseEntity<List<BookingRes>> obtenerTodasLasBookings() {
        List<BookingRes> bookings = this.bookingService.obtenerTodasLasBookings();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    /**
     * Obtiene una booking por su ID.
     *
     * @param id El identificador único de la booking que se desea obtener.
     * @return Una respuesta HTTP con el objeto BookingModel encontrado y el código
     *         de estado 200 (OK)
     *         si la booking existe, o código de estado 404 (NOT FOUND) si no se
     *         encuentra.
     */
    @Operation(summary = "Obtener una reserva", description = "Obtiene una reserva registrada por su id en el sistema.", responses = {
            @ApiResponse(responseCode = "200", description = "Reserva obtenida exitosamente", content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserRegisterRes.class)), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "No se encontro la reserva", content = @Content(mediaType = "application/json"))
    })
    @Parameters({
            @Parameter(name = "id", description = "ID de la reserva a obtener", required = true, in = ParameterIn.PATH)
    })
    @GetMapping("/{id}")
    public ResponseEntity<BookingRes> obtenerBookingPorId(@PathVariable Integer id) {
        Optional<BookingRes> booking = this.bookingService.obtenerBookingPorId(id);
        if (booking.isPresent()) {
            return new ResponseEntity<>(booking.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Actualiza una booking por su ID.
     *
     * @param id                 El identificador único de la booking que se desea
     *                           actualizar.
     * @param bookingActualizada El objeto BookingModel con los datos actualizados.
     * @return Una respuesta HTTP con el objeto BookingModel actualizado y el código
     *         de estado 200 (OK)
     *         si la booking existe, o código de estado 404 (NOT FOUND) si no se
     *         encuentra.
     */
    @Operation(summary = "Actualiza una reserva", description = "Actualiza una reserva por su id en el sistema.", responses = {
            @ApiResponse(responseCode = "200", description = "reserva actualizada exitosamente", content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserRegisterRes.class)), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "No se encontro la reserva", content = @Content(mediaType = "application/json"))
    })
    @Parameters({
            @Parameter(name = "id", description = "ID de la reserva a obtener", required = true, in = ParameterIn.PATH)
    })
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarBooking(@PathVariable Integer id,
            @RequestBody BookingReq bookingActualizada) {

        Optional<BookingRes> bookingExistente = this.bookingService.obtenerBookingPorId(id);
        if (bookingExistente.isPresent()) {
            return bookingService.actualizarBooking(id, bookingActualizada);
        } else {
            return new ResponseEntity<String>("Reserva no encontrada",HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Elimina una booking por su ID.
     *
     * @param id El identificador único de la booking que se desea eliminar.
     * @return Una respuesta HTTP con el código de estado 204 (NO CONTENT) si la
     *         booking se eliminó con éxito,
     *         o código de estado 404 (NOT FOUND) si no se encuentra.
     */
    @Operation(summary = "Elimina una reserva", description = "Elimina una reserva por su id en el sistema.", responses = {
            @ApiResponse(responseCode = "200", description = "Reserva eliminada exitosamente", content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserRegisterRes.class)), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "No se encontro la reserva", content = @Content(mediaType = "application/json"))
    })
    @Parameters({
            @Parameter(name = "id", description = "ID de la reserva a eliminar", required = true, in = ParameterIn.PATH)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarBooking(@PathVariable Integer id) {
        Optional<BookingRes> bookingExistente = this.bookingService.obtenerBookingPorId(id);
        if (bookingExistente.isPresent()) {
            bookingService.eliminarBooking(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Obtener las reservas asociadas a un usuario", description = "Obtiene las reservas registradas por un usuario a través de su id.", responses = {
            @ApiResponse(responseCode = "200", description = "Reservas obtenidas exitosamente", content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserRegisterRes.class)), mediaType = "application/json")),
    })
    @Parameters({
            @Parameter(name = "id", description = "ID del usuario con las reservas a obtener", required = true, in = ParameterIn.PATH)
    })
    @GetMapping("/user/{UserID}")
    public ResponseEntity<List<BookingRes>> getUserBookings(@PathVariable Integer UserID) {
        List<BookingRes> bookings = this.bookingService.getUserBookings(UserID);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }


}