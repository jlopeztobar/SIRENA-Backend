package co.edu.unicauca.SIRENABackend.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.unicauca.SIRENABackend.models.RoleModel;
import co.edu.unicauca.SIRENABackend.repositories.IRoleRepository;

/**
 * Servicio para gestionar roles de usuarios.
 */
@Service
public class RoleService {
    @Autowired
    IRoleRepository roleRepository;

    /**
     * Obtiene una lista de todos los roles disponibles.
     *
     * @return Una lista de objetos RoleModel que representan todos los roles.
     */
    @Transactional(readOnly = true)
    public ArrayList<RoleModel> getRoles() {
        return (ArrayList<RoleModel>) roleRepository.findAll();
    }

    /**
     * Guarda un nuevo rol en la base de datos.
     *
     * @param prmRole El objeto RoleModel que se desea guardar.
     * @return La instancia de RoleModel guardada en la base de datos.
     */
    @Transactional
    public RoleModel saveRole(RoleModel prmRole) {
        return roleRepository.save(prmRole);
    }

    /**
     * Obtiene un rol por su identificador único.
     *
     * @param prmId El identificador único del rol que se desea obtener.
     * @return Un objeto Optional que contiene el rol si se encuentra, o vacío si no se encuentra.
     */
    @Transactional(readOnly = true)
    public Optional<RoleModel> getRoleById(Integer prmId) {
        return roleRepository.findById(prmId);
    }

    /**
     * Elimina un rol por su identificador único.
     *
     * @param prmId El identificador único del rol que se desea eliminar.
     * @return `true` si el rol se eliminó con éxito, `false` si no se pudo eliminar.
     */
    @Transactional
    public boolean deleteRoleById(Integer prmId) {
        try {
            roleRepository.deleteById(prmId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
