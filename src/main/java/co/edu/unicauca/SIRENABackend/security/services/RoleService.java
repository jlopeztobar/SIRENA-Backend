package co.edu.unicauca.SIRENABackend.security.services;

import java.util.ArrayList;
import java.util.Optional;

import co.edu.unicauca.SIRENABackend.security.common.enums.RoleEnum;
import co.edu.unicauca.SIRENABackend.security.dtos.request.RoleReq;
import co.edu.unicauca.SIRENABackend.security.dtos.response.RoleRes;

/**
 * Servicio que proporciona operaciones relacionadas con roles en la aplicación.
 */
public interface RoleService {
    /**
     * Obtiene la lista de roles disponibles.
     *
     * @return Lista de roles representados como objetos {@code RoleRes}.
     */
    ArrayList<RoleRes> getRoles();

    /**
     * Obtiene un rol por su nombre.
     *
     * @param prmRoleName Nombre del rol.
     * @return Un objeto {@code RoleRes} si el rol existe, de lo contrario, un valor opcional vacío.
     */
    Optional<RoleRes> getByRoleName(RoleEnum prmRoleName);

    /**
     * Guarda un nuevo rol en la aplicación.
     *
     * @param prmRole Objeto de solicitud que contiene la información del nuevo rol.
     * @return Objeto {@code RoleRes} que representa el rol guardado.
     */
    RoleRes saveRole(RoleReq prmRole);

    /**
     * Obtiene un rol por su identificador único.
     *
     * @param prmId Identificador único del rol.
     * @return Un objeto {@code RoleRes} si el rol existe, de lo contrario, un valor opcional vacío.
     */
    Optional<RoleRes> getRoleById(Integer prmId);

}
