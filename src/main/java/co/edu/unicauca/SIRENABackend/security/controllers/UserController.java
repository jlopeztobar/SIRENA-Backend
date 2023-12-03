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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.SIRENABackend.security.dtos.request.UserRegisterReq;
import co.edu.unicauca.SIRENABackend.security.dtos.response.UserRegisterRes;
import co.edu.unicauca.SIRENABackend.security.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

/**
 * Controlador que maneja los endpoints relacionados con los usuarios.
 * Proporciona funcionalidades para obtener todos los usuarios, obtener un usuario por su ID, registrar un nuevo usuario,
 * desactivar y activar usuarios.
 */
@RestController
@RequestMapping("/user")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "user controller", description = "Endpoints para usuario")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * Obtiene una lista de todos los usuarios registrados en el sistema.
     *
     * @return ResponseEntity con la lista de usuarios en caso de éxito, o un ResponseEntity con código 404 si no se encuentran usuarios.
     */
    @Operation(summary = "Obtener todos los usuarios", description = "Obtiene una lista de todos los usuarios registrados en el sistema.", responses = {
            @ApiResponse(responseCode = "200", description = "Usuarios obtenidos exitosamente", content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserRegisterRes.class)), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "No se encontraron usuarios", content = @Content(mediaType = "application/json"))
    })
    @GetMapping()
    public ResponseEntity<ArrayList<UserRegisterRes>> getUsers() {
        ArrayList<UserRegisterRes> users = this.userService.getUsers();

        if (!users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Obtiene un usuario específico por su ID.
     *
     * @param userID ID del usuario a obtener.
     * @return ResponseEntity con el usuario en caso de éxito, o un ResponseEntity con código 404 si el usuario no se encuentra.
     */
    @Operation(summary = "Registrar un nuevo usuario", description = "Registra un nuevo usuario en el sistema.", responses = {
            @ApiResponse(responseCode = "201", description = "Usuario registrado exitosamente", content = @Content(schema = @Schema(implementation = UserRegisterRes.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "No se encontro el usuario", content = @Content(mediaType = "application/json"))
    })
    @Parameters({
            @Parameter(name = "id", description = "ID del usuario a obtener", required = true, in = ParameterIn.PATH)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserRegisterRes>> getUserById(@PathVariable("id") Integer userID) {
        Optional<UserRegisterRes> user = this.userService.getUserById(userID);

        if (user.isPresent()) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Registra un nuevo usuario en el sistema.
     *
     * @param prmUser Datos del nuevo usuario.
     * @return ResponseEntity con el usuario registrado en caso de éxito, o un ResponseEntity con código 400 si la solicitud es incorrecta.
     */
    @Operation(summary = "Registrar un nuevo usuario", description = "Registra un nuevo usuario en el sistema.", responses = {
            @ApiResponse(responseCode = "201", description = "Usuario registrado exitosamente", content = @Content(schema = @Schema(implementation = UserRegisterRes.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "La solicitud es incorrecta", content = @Content(mediaType = "application/json"))
    })
    @PostMapping()
    public ResponseEntity<UserRegisterRes> saveUser(@RequestBody UserRegisterReq prmUser) {
        UserRegisterRes savedUser = this.userService.saveUser(prmUser);

        if (savedUser != null) {
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Desactiva un usuario por su ID.
     *
     * @param userID ID del usuario a desactivar.
     * @return ResponseEntity con mensaje de éxito o error.
     */
    @Operation(summary = "Desactivar usuario", description = "Desactiva un usuario por su ID.", responses = {
            @ApiResponse(responseCode = "200", description = "Usuario desactivado exitosamente", content = @Content(schema = @Schema(implementation = String.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "La solicitud es incorrecta", content = @Content(schema = @Schema(implementation = String.class), mediaType = "application/json"))
    })
    @Parameters({
            @Parameter(name = "id", description = "ID del usuario a desativar", required = true, in = ParameterIn.PATH)
    })
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<String> deactivateUser(@PathVariable("id") Integer userID) {
        if (this.userService.deactivateUser(userID)) {
            return new ResponseEntity<>("Usuario desactivado exitosamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error al desactivar el usuario", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Activa un usuario por su ID.
     *
     * @param userID ID del usuario a activar.
     * @return ResponseEntity con mensaje de éxito o error.
     */
    @Operation(summary = "Activar usuario", description = "Activa un usuario por su ID.", responses = {
            @ApiResponse(responseCode = "200", description = "Usuario activado exitosamente", content = @Content(schema = @Schema(implementation = String.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "La solicitud es incorrecta", content = @Content(schema = @Schema(implementation = String.class), mediaType = "application/json"))
    })
    @Parameters({
            @Parameter(name = "id", description = "ID del usuario a desactivar", required = true, in = ParameterIn.PATH)
    })
    @PutMapping("/{id}/activate")
    public ResponseEntity<String> activateUser(@PathVariable("id") Integer userID) {
        if (this.userService.activateUser(userID)) {
            return new ResponseEntity<>("Usuario activado exitosamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error al activar el usuario", HttpStatus.BAD_REQUEST);
        }
    }

}