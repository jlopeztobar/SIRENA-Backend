package co.edu.unicauca.SIRENABackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unicauca.SIRENABackend.models.ClassroomModel;

public interface IClassroomRepository extends JpaRepository<ClassroomModel, Integer> {

}
