package co.edu.unicauca.SIRENABackend.services;

import co.edu.unicauca.SIRENABackend.models.BuildingModel;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz que define los servicios relacionados con los edificios en el sistema.
 */
public interface BuildingService {

    /**
     * Obtiene todos los edificios en el sistema.
     *
     * @return Lista de todos los edificios.
     */
    List<BuildingModel> getBuildings();

    /**
     * Guarda un nuevo edificio en el sistema.
     *
     * @param prmIncidence El edificio que se va a guardar.
     * @return El edificio guardado.
     */
    BuildingModel saveBuilding(BuildingModel prmIncidence);

    /**
     * Obtiene un edificio por su ID.
     *
     * @param prmId El ID del edificio que se va a obtener.
     * @return Un {@link Optional} que contiene el edificio si se encuentra, o vacío si no.
     */
    Optional<BuildingModel> getBuildingById(Integer prmId);

    /**
     * Elimina un edificio por su ID.
     *
     * @param prmId El ID del edificio que se va a eliminar.
     * @return `true` si el edificio se eliminó con éxito, `false` si no.
     */
    boolean deleteBuildingById(Integer prmId);
}
