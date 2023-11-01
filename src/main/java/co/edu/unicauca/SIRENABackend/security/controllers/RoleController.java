package co.edu.unicauca.SIRENABackend.security.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.SIRENABackend.security.models.RoleModel;
import co.edu.unicauca.SIRENABackend.security.services.RoleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/role")
@SecurityRequirement(name = "bearerAuth")
public class RoleController {

    @Autowired
    RoleService roleService;

    /**
     * Obtiene una lista de todos los roles disponibles.
     *
     * @return Una lista de objetos RoleModel que representan todos los roles.
     */
    @GetMapping()
    public ResponseEntity<ArrayList<RoleModel>> getRoles() {
        ArrayList<RoleModel> roles = this.roleService.getRoles();

        if (!roles.isEmpty()) {
            return new ResponseEntity<>(roles, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Obtiene un rol por su ID.
     *
     * @param roleID El identificador único del rol que se desea obtener.
     * @return Un objeto Optional que contiene el rol si se encuentra, o vacío si no
     *         se encuentra.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Optional<RoleModel>> getRoleById(@PathVariable("id") Integer roleID) {
        Optional<RoleModel> role = this.roleService.getRoleById(roleID);

        if (role.isPresent()) {
            return new ResponseEntity<>(role, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
