package co.edu.unicauca.SIRENABackend.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unicauca.SIRENABackend.common.enums.IncidenceTypeEnum;
import co.edu.unicauca.SIRENABackend.models.IncidenceTypeModel;


/**
 * Repositorio para acceder a la persistencia de las entidades IncidenceTypeModel.
 */
public interface IIncidenceTypeRepository extends JpaRepository<IncidenceTypeModel, Integer> {
    /**
     * Recupera todos los tipos de incidencia almacenados.
     *
     * @return Lista de tipos de incidencia.
     */
    public ArrayList<IncidenceTypeModel> findAll();

    /**
     * Verifica si un tipo de incidencia con el nombre especificado existe en la base de datos.
     *
     * @param name Nombre del tipo de incidencia.
     * @return True si existe, false en caso contrario.
     */
    public boolean existsByName(IncidenceTypeEnum name);

    /**
     * Busca un tipo de incidencia por su nombre.
     *
     * @param name Nombre del tipo de incidencia.
     * @return Tipo de incidencia encontrado o null si no existe.
     */
    public IncidenceTypeModel findByName(IncidenceTypeEnum name);

}
