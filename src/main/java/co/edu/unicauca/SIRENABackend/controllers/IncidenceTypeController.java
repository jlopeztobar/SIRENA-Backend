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

import co.edu.unicauca.SIRENABackend.models.IncidenceTypeModel;
import co.edu.unicauca.SIRENABackend.services.IncidenceTypeService;

@RestController
@RequestMapping("/incidenceType")
public class IncidenceTypeController {
    @Autowired
    IncidenceTypeService incidenceTypeService;

    /**
     * Obtiene una lista de tipos de incidencia.
     *
     * @return ArrayList de IncidenceTypeModel que representa los tipos de
     *         incidencia.
     */
    @GetMapping()
    public ResponseEntity<ArrayList<IncidenceTypeModel>> getIncidenceTypes() {
        ArrayList<IncidenceTypeModel> incidenceTypes = incidenceTypeService.getIncidenceTypes();

        if (!incidenceTypes.isEmpty()) {
            return new ResponseEntity<>(incidenceTypes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Guarda un nuevo tipo de incidencia.
     *
     * @param prmIncidence El tipo de incidencia a guardar.
     * @return El IncidenceTypeModel que ha sido guardado.
     */
    @PostMapping()
    public ResponseEntity<IncidenceTypeModel> saveIncidenceTypes(@RequestBody IncidenceTypeModel prmIncidence) {
        IncidenceTypeModel savedIncidenceType = this.incidenceTypeService.saveIncidenceTypes(prmIncidence);

        if (savedIncidenceType != null) {
            return new ResponseEntity<>(savedIncidenceType, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Obtiene un tipo de incidencia por su ID.
     *
     * @param incidenceTypeID El ID del tipo de incidencia a obtener.
     * @return Un Optional que puede contener un IncidenceTypeModel si se encuentra,
     *         de lo contrario, es vacío.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Optional<IncidenceTypeModel>> getIncidenceTypeById(
            @PathVariable("id") Integer incidenceTypeID) {
        Optional<IncidenceTypeModel> incidenceType = this.incidenceTypeService.getIncidenceTypeById(incidenceTypeID);

        if (incidenceType.isPresent()) {
            return new ResponseEntity<>(incidenceType, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Elimina un tipo de incidencia por su ID.
     *
     * @param incidenceTypeID El ID del tipo de incidencia a eliminar.
     * @return Un mensaje que indica si se ha eliminado correctamente el tipo de
     *         incidencia o no.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Integer incidenceTypeID) {
        boolean confirmation = this.incidenceTypeService.deleteIncidenceTypeById(incidenceTypeID);

        if (confirmation) {
            return new ResponseEntity<>("Se ha eliminado el tipo de incidencia con id = " + incidenceTypeID,
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se eliminó el tipo de incidencia con id = " + incidenceTypeID,
                    HttpStatus.NOT_FOUND);
        }
    }

}
