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

import co.edu.unicauca.SIRENABackend.models.RoleModel;
import co.edu.unicauca.SIRENABackend.services.RoleService;

@RestController
@RequestMapping("/api/v1/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    /**
     * Obtiene una lista de todos los roles disponibles.
     *
     * @return Una lista de objetos RoleModel que representan todos los roles.
     */
    @GetMapping()
    public ArrayList<RoleModel> getRoles() {
        return roleService.getRoles();
    }

    /**
     * Guarda un nuevo rol.
     *
     * @param prmRole El objeto RoleModel que se desea guardar.
     * @return El objeto RoleModel guardado en la base de datos.
     */
    @PostMapping()
    public RoleModel saveRole(@RequestBody RoleModel prmRole) {
        return this.roleService.saveRole(prmRole);
    }

    /**
     * Obtiene un rol por su ID.
     *
     * @param roleID El identificador único del rol que se desea obtener.
     * @return Un objeto Optional que contiene el rol si se encuentra, o vacío si no se encuentra.
     */
    @GetMapping(path = "/{id}")
    public Optional<RoleModel> getRoleById(@PathVariable("id") Integer roleID) {
        return this.roleService.getRoleById(roleID);
    }

    /**
     * Elimina un rol por su ID.
     *
     * @param roleID El identificador único del rol que se desea eliminar.
     * @return Un mensaje que indica si se eliminó o no el rol con el ID proporcionado.
     */
    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Integer roleID) {
        boolean confirmation = this.roleService.deleteRoleById(roleID);
        if (confirmation) {
            return "Se ha eliminado el rol con id = " + roleID;
        } else {
            return "No se eliminó el rol con id = " + roleID;
        }
    }
}
