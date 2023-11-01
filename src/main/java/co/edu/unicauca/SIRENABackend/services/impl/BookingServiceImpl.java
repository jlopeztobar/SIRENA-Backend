package co.edu.unicauca.SIRENABackend.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unicauca.SIRENABackend.models.BookingModel;
import co.edu.unicauca.SIRENABackend.repositories.IBookingRepository;
import co.edu.unicauca.SIRENABackend.services.BookingService;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private IBookingRepository bookingRepository;

    public BookingModel crearBooking(BookingModel bookingModel) {
        return bookingRepository.save(bookingModel);
    }

    public List<BookingModel> obtenerTodasLasBookings() {
        return bookingRepository.findAll();
    }

    public Optional<BookingModel> obtenerBookingPorId(Integer id) {
        return bookingRepository.findById(id);
    }

    public BookingModel actualizarBooking(Integer id, BookingModel bookingActualizada) {
        Optional<BookingModel> bookingExistente = bookingRepository.findById(id);
        if (bookingExistente.isPresent()) {
            bookingActualizada.setId(id);
            return bookingRepository.save(bookingActualizada);
        } else {
            throw new RuntimeException("La booking no se encontró con el ID proporcionado.");
        }
    }

    public void eliminarBooking(Integer id) {
        Optional<BookingModel> bookingExistente = bookingRepository.findById(id);
        if (bookingExistente.isPresent()) {
            bookingRepository.deleteById(id);
        } else {
            throw new RuntimeException("La booking no se encontró con el ID proporcionado.");
        }
    }
}