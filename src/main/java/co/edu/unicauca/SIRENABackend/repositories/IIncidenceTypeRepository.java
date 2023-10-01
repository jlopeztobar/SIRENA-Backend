package co.edu.unicauca.SIRENABackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unicauca.SIRENABackend.models.IncidenceTypeModel;

public interface IIncidenceTypeRepository extends JpaRepository<IncidenceTypeModel, Integer>{
    
}
