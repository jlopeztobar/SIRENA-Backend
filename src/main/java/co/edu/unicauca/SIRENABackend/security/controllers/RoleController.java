package co.edu.unicauca.SIRENABackend.security.controllers;

import java.util.ArrayList;
import java.util.Optional;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.SIRENABackend.security.dtos.response.RoleRes;
import co.edu.unicauca.SIRENABackend.security.services.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

/**
 * Controlador que maneja los endpoints relacionados con los roles de usuario.
 * Proporciona funcionalidades para obtener una lista de roles y obtener un rol por su ID.
 */
@RestController
@RequestMapping("/role")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Role controller", description = "Endpoints para los diferentes Rol")
public class RoleController {

    @Autowired
    RoleService roleService;

    /**
     * Obtiene una lista de roles.
     *
     * @return ResponseEntity con la lista de roles en caso de éxito, o un ResponseEntity con código 404 si no se encuentran roles.
     */
    @Operation(summary = "Obtener un rol por ID", description = "Obtiene un rol específico por su ID.", responses = {
            @ApiResponse(responseCode = "200", description = "Rol obtenido exitosamente", content = @Content(schema = @Schema(implementation = RoleRes.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })
    @GetMapping()
    public ResponseEntity<ArrayList<RoleRes>> getRoles() {
        ArrayList<RoleRes> roles = this.roleService.getRoles();

        if (!roles.isEmpty()) {
            return new ResponseEntity<>(roles, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Obtiene un rol específico por su ID.
     *
     * @param roleID ID del rol a obtener.
     * @return ResponseEntity con el rol en caso de éxito, o un ResponseEntity con código 404 si el rol no se encuentra.
     */
    @Operation(summary = "Obtener un rol por ID", description = "Obtiene un rol específico por su ID.", responses = {
            @ApiResponse(responseCode = "200", description = "Rol obtenido exitosamente", content = @Content(schema = @Schema(implementation = RoleRes.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })
    @Parameters({
            @Parameter(name = "id", description = "ID del rol a obtener", required = true, in = ParameterIn.PATH)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Optional<RoleRes>> getRoleById(@PathVariable("id") Integer roleID) {
        Optional<RoleRes> role = this.roleService.getRoleById(roleID);

        if (role.isPresent()) {
            return new ResponseEntity<>(role, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
