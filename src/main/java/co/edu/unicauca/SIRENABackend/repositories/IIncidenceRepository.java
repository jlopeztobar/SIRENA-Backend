package co.edu.unicauca.SIRENABackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unicauca.SIRENABackend.models.IncidenceModel;

public interface IIncidenceRepository extends JpaRepository<IncidenceModel, Integer>{
    
}
