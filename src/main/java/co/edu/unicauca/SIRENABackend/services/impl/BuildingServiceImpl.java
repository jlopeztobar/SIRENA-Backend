package co.edu.unicauca.SIRENABackend.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.unicauca.SIRENABackend.models.BuildingModel;
import co.edu.unicauca.SIRENABackend.repositories.IBuildingRespository;
import co.edu.unicauca.SIRENABackend.services.BuildingService;

/**
 * Implementación de {@link BuildingService} que interactúa con la capa de repositorio para realizar operaciones
 * relacionadas con los edificios.
 */
@Service
public class BuildingServiceImpl implements BuildingService {

    /**
     * Constructor utilizado para la inyección de dependencias.
     *
     * @param buildingRepository Repositorio de edificios.
     */
    @Autowired
    private IBuildingRespository buildingReposirtory;

    /**
     * Guarda un edificio en el sistema.
     *
     * @param faculty El edificio que se va a guardar.
     * @return El edificio guardado.
     */
    @Transactional(readOnly = false)
    public BuildingModel saveBuilding(BuildingModel faculty) {
        return buildingReposirtory.save(faculty);
    }

    /**
     * Elimina un edificio por su ID.
     *
     * @param id El ID del edificio que se va a eliminar.
     * @return {@code true} si el edificio se eliminó con éxito, {@code false} si no.
     */
    @Transactional(readOnly = false)
    public boolean deleteBuildingById(Integer id) {
        try {
            buildingReposirtory.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Obtiene un edificio por su ID.
     *
     * @param id El ID del edificio que se va a obtener.
     * @return Un {@link Optional} que contiene el edificio si se encuentra, o vacío si no.
     */
    @Transactional(readOnly = true)
    public Optional<BuildingModel> getBuildingById(Integer id) {
        return buildingReposirtory.findById(id);
    }

    /**
     * Obtiene todos los edificios en el sistema.
     *
     * @return Lista de todos los edificios.
     */
    @Transactional(readOnly = true)
    public List<BuildingModel> getBuildings() {
        return (List<BuildingModel>) buildingReposirtory.findAll();
    }

}
