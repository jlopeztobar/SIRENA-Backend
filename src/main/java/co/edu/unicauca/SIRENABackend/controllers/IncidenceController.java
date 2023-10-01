package co.edu.unicauca.SIRENABackend.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.SIRENABackend.models.IncidenceModel;
import co.edu.unicauca.SIRENABackend.services.IncidenceService;

@RestController
@RequestMapping("/api/v1/incidence")
public class IncidenceController {
    @Autowired
    IncidenceService incidenceService;

    /**
     * Obtiene una lista de incidencias.
     *
     * @return ArrayList de IncidenceModel que representa las incidencias.
     */
    @GetMapping()
    public ArrayList<IncidenceModel> getIncidences() {
        return incidenceService.getIncidences();
    }

    /**
     * Guarda una nueva incidencia.
     *
     * @param prmIncidence La incidencia a guardar.
     * @return El IncidenceModel que ha sido guardado.
     */
    @PostMapping()
    public IncidenceModel saveIncidence(@RequestBody IncidenceModel prmIncidence) {
        return this.incidenceService.saveIncidence(prmIncidence);
    }

    /**
     * Obtiene una incidencia por su ID.
     *
     * @param incidenceID El ID de la incidencia a obtener.
     * @return Un Optional que puede contener un IncidenceModel si se encuentra, de lo contrario, es vacío.
     */
    @GetMapping(path = "/{id}")
    public Optional<IncidenceModel> getIncidenceById(@PathVariable("id") Integer incidenceID) {
        return this.incidenceService.getIncidenceById(incidenceID);
    }

    /**
     * Elimina una incidencia por su ID.
     *
     * @param incidenceID El ID de la incidencia a eliminar.
     * @return Un mensaje que indica si se ha eliminado correctamente la incidencia o no.
     */
    @DeleteMapping(path = "/{id}")
    public String deleteIncidenceById(@PathVariable("id") Integer incidenceID) {
        boolean confirmation = this.incidenceService.deleteIncidenceById(incidenceID);
        if (confirmation) {
            return "Se ha eliminado el incidencia con id = " + incidenceID;
        } else {
            return "No se eliminó el incidencia con id = " + incidenceID;
        }
    }
}
