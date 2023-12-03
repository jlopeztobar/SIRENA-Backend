package co.edu.unicauca.SIRENABackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.unicauca.SIRENABackend.models.BookingModel;

/**
 * Repositorio para acceder a la persistencia de las entidades BookingModel.
 */
@Repository
public interface IBookingRepository extends JpaRepository<BookingModel, Integer> {

}
