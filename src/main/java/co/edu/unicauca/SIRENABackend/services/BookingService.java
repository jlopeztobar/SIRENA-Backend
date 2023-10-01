package co.edu.unicauca.SIRENABackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unicauca.SIRENABackend.models.BookingModel;
import co.edu.unicauca.SIRENABackend.repositories.IBookingRepository;

/**
 * Servicio para gestionar bookings.
 */
@Service
public class BookingService {

    @Autowired
    private IBookingRepository bookingRepository;

    /**
     * Constructor para inyectar una instancia de IBookingRepository.
     *
     * @param bookingRepository Repositorio de bookings.
     */
    @Autowired
    public BookingService(IBookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    /**
     * Crea una nueva booking.
     *
     * @param bookingModel El objeto BookingModel que se desea crear.
     * @return La instancia de BookingModel creada y almacenada en la base de datos.
     */
    public BookingModel crearBooking(BookingModel bookingModel) {
        return bookingRepository.save(bookingModel);
    }

    /**
     * Obtiene todas las bookings almacenadas en la base de datos.
     *
     * @return Una lista de objetos BookingModel que representan todas las bookings.
     */
    public List<BookingModel> obtenerTodasLasBookings() {
        return bookingRepository.findAll();
    }

    /**
     * Obtiene una booking por su identificador único.
     *
     * @param id El identificador único de la booking que se desea obtener.
     * @return Un objeto Optional que contiene la booking si se encuentra, o vacío si no se encuentra.
     */
    public Optional<BookingModel> obtenerBookingPorId(Integer id) {
        return bookingRepository.findById(id);
    }

    /**
     * Actualiza una booking existente por su identificador único.
     *
     * @param id                  El identificador único de la booking que se desea actualizar.
     * @param bookingActualizada  El objeto BookingModel con los datos actualizados.
     * @return La instancia de BookingModel actualizada y almacenada en la base de datos.
     * @throws RuntimeException Si la booking no se encuentra con el ID proporcionado.
     */
    public BookingModel actualizarBooking(Integer id, BookingModel bookingActualizada) {
        Optional<BookingModel> bookingExistente = bookingRepository.findById(id);
        if (bookingExistente.isPresent()) {
            bookingActualizada.setId(id);
            return bookingRepository.save(bookingActualizada);
        } else {
            throw new RuntimeException("La booking no se encontró con el ID proporcionado.");
        }
    }

    /**
     * Elimina una booking por su identificador único.
     *
     * @param id El identificador único de la booking que se desea eliminar.
     * @throws RuntimeException Si la booking no se encuentra con el ID proporcionado.
     */
    public void eliminarBooking(Integer id) {
        Optional<BookingModel> bookingExistente = bookingRepository.findById(id);
        if (bookingExistente.isPresent()) {
            bookingRepository.deleteById(id);
        } else {
            throw new RuntimeException("La booking no se encontró con el ID proporcionado.");
        }
    }
}