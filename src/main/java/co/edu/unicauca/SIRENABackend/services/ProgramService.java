package co.edu.unicauca.SIRENABackend.services;

import java.util.List;
import java.util.Optional;

import co.edu.unicauca.SIRENABackend.models.ProgramModel;

public interface ProgramService {
    /**
     * Obtiene todos los programas en el sistema.
     *
     * @return Lista de todos los programas.
     */
    List<ProgramModel> getPrograms();

    /**
     * Guarda un nuevo programa en el sistema.
     *
     * @param prmIncidence El programa que se va a guardar.
     * @return El programa guardado.
     */
    ProgramModel saveProgram(ProgramModel prmIncidence);

    /**
     * Obtiene un programa por su ID.
     *
     * @param prmId El ID del programa que se va a obtener.
     * @return Un {@link Optional} que contiene el programa si se encuentra, o vacío
     *         si no.
     */
    Optional<ProgramModel> getProgramById(Integer prmId);

    /**
     * Elimina un programa por su ID.
     *
     * @param prmId El ID del programa que se va a eliminar.
     * @return `true` si el programa se eliminó con éxito, `false` si no.
     */
    boolean deleteProgramById(Integer prmId);
}
