package co.edu.unicauca.SIRENABackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.unicauca.SIRENABackend.models.BookingModel;

@Repository
public interface IBookingRepository extends JpaRepository<BookingModel, Integer> {

}
