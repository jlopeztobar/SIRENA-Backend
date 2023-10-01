package co.edu.unicauca.SIRENABackend.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.SIRENABackend.models.BookingModel;
import co.edu.unicauca.SIRENABackend.repositories.IBookingRepository;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private final IBookingRepository bookingRepository;

    @Autowired
    public BookingController(IBookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    /**
     * Crea una nueva booking.
     *
     * @param bookingModel El objeto BookingModel que se desea crear y guardar.
     * @return Una respuesta HTTP con el objeto BookingModel creado y el código de estado 201 (CREATED).
     */
    public ResponseEntity<BookingModel> crearBooking(@RequestBody BookingModel bookingModel) {
        BookingModel nuevaBooking = bookingRepository.save(bookingModel);
        return new ResponseEntity<>(nuevaBooking, HttpStatus.CREATED);
    }

    /**
     * Obtiene todas las bookings almacenadas en la base de datos.
     *
     * @return Una respuesta HTTP con una lista de objetos BookingModel y el código de estado 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<BookingModel>> obtenerTodasLasBookings() {
        List<BookingModel> bookings = bookingRepository.findAll();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    /**
     * Obtiene una booking por su ID.
     *
     * @param id El identificador único de la booking que se desea obtener.
     * @return Una respuesta HTTP con el objeto BookingModel encontrado y el código de estado 200 (OK)
     *         si la booking existe, o código de estado 404 (NOT FOUND) si no se encuentra.
     */
    @GetMapping("/{id}")
    public ResponseEntity<BookingModel> obtenerBookingPorId(@PathVariable Integer id) {
        Optional<BookingModel> booking = bookingRepository.findById(id);
        if (booking.isPresent()) {
            return new ResponseEntity<>(booking.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Actualiza una booking por su ID.
     *
     * @param id                  El identificador único de la booking que se desea actualizar.
     * @param bookingActualizada  El objeto BookingModel con los datos actualizados.
     * @return Una respuesta HTTP con el objeto BookingModel actualizado y el código de estado 200 (OK)
     *         si la booking existe, o código de estado 404 (NOT FOUND) si no se encuentra.
     */
    @PutMapping("/{id}")
    public ResponseEntity<BookingModel> actualizarBooking(@PathVariable Integer id, @RequestBody BookingModel bookingActualizada) {
        Optional<BookingModel> bookingExistente = bookingRepository.findById(id);
        if (bookingExistente.isPresent()) {
            bookingActualizada.setId(id);
            bookingRepository.save(bookingActualizada);
            return new ResponseEntity<>(bookingActualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Elimina una booking por su ID.
     *
     * @param id El identificador único de la booking que se desea eliminar.
     * @return Una respuesta HTTP con el código de estado 204 (NO CONTENT) si la booking se eliminó con éxito,
     *         o código de estado 404 (NOT FOUND) si no se encuentra.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarBooking(@PathVariable Integer id) {
        Optional<BookingModel> bookingExistente = bookingRepository.findById(id);
        if (bookingExistente.isPresent()) {
            bookingRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}