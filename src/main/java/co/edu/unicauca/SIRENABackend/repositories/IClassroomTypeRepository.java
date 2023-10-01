package co.edu.unicauca.SIRENABackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unicauca.SIRENABackend.models.ClassroomTypeModel;

public interface IClassroomTypeRepository extends JpaRepository<ClassroomTypeModel, Integer> {
    
}
