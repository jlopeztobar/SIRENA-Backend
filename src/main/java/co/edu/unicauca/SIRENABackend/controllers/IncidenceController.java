package co.edu.unicauca.SIRENABackend.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/incidence")
public class IncidenceController {
    @Autowired
    IncidenceService incidenceService;

    /**
     * Obtiene una lista de incidencias.
     *
     * @return ArrayList de IncidenceModel que representa las incidencias.
     */
    @GetMapping()
    public ResponseEntity<ArrayList<IncidenceModel>> getIncidences() {
        ArrayList<IncidenceModel> incidences = incidenceService.getIncidences();

        if (!incidences.isEmpty()) {
            return new ResponseEntity<>(incidences, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Guarda una nueva incidencia.
     *
     * @param prmIncidence La incidencia a guardar.
     * @return El IncidenceModel que ha sido guardado.
     */
    @PostMapping()
    public ResponseEntity<IncidenceModel> saveIncidence(@RequestBody IncidenceModel prmIncidence) {
        IncidenceModel savedIncidence = this.incidenceService.saveIncidence(prmIncidence);

        if (savedIncidence != null) {
            return new ResponseEntity<>(savedIncidence, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Obtiene una incidencia por su ID.
     *
     * @param incidenceID El ID de la incidencia a obtener.
     * @return Un Optional que puede contener un IncidenceModel si se encuentra, de
     *         lo contrario, es vacío.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Optional<IncidenceModel>> getIncidenceById(@PathVariable("id") Integer incidenceID) {
        Optional<IncidenceModel> incidence = this.incidenceService.getIncidenceById(incidenceID);

        if (incidence.isPresent()) {
            return new ResponseEntity<>(incidence, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Elimina una incidencia por su ID.
     *
     * @param incidenceID El ID de la incidencia a eliminar.
     * @return Un mensaje que indica si se ha eliminado correctamente la incidencia
     *         o no.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteIncidenceById(@PathVariable("id") Integer incidenceID) {
        boolean confirmation = this.incidenceService.deleteIncidenceById(incidenceID);

        if (confirmation) {
            return new ResponseEntity<>("Se ha eliminado la incidencia con id = " + incidenceID, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se eliminó la incidencia con id = " + incidenceID, HttpStatus.NOT_FOUND);
        }
    }

}
