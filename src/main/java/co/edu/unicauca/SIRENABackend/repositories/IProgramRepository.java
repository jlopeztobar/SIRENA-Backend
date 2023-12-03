package co.edu.unicauca.SIRENABackend.repositories;


import co.edu.unicauca.SIRENABackend.models.ProgramModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProgramRepository extends JpaRepository<ProgramModel, Integer> {
}
