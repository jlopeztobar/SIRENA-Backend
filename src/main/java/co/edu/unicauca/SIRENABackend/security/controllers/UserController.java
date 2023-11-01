package co.edu.unicauca.SIRENABackend.security.controllers;

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

import co.edu.unicauca.SIRENABackend.security.dtos.request.UserRegisterReq;
import co.edu.unicauca.SIRENABackend.security.dtos.response.UserRes;
import co.edu.unicauca.SIRENABackend.security.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/user")
@SecurityRequirement(name = "bearerAuth")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * Obtiene una lista de todos los usuarios registrados.
     *
     * @return Una lista de objetos UserModel que representan a todos los usuarios.
     */
    @GetMapping()
    public ResponseEntity<ArrayList<UserRes>> getUsers() {
        ArrayList<UserRes> users = this.userService.getUsers();

        if (!users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Guarda un nuevo usuario.
     *
     * @param prmUser El objeto UserModel que se desea guardar.
     * @return El objeto UserModel guardado en la base de datos.
     */
    @PostMapping()
    public ResponseEntity<UserRes> saveUser(@RequestBody UserRegisterReq prmUser) {
        UserRes savedUser = this.userService.saveUser(prmUser);

        if (savedUser != null) {
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Obtiene un usuario por su ID.
     *
     * @param userID El identificador único del usuario que se desea obtener.
     * @return Un objeto Optional que contiene el usuario si se encuentra, o vacío
     *         si no se encuentra.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserRes>> getUserById(@PathVariable("id") Integer userID) {
        Optional<UserRes> user = this.userService.getUserById(userID);

        if (user.isPresent()) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Elimina un usuario por su ID.
     *
     * @param userID El identificador único del usuario que se desea eliminar.
     * @return Un mensaje que indica si se eliminó o no el usuario con el ID
     *         proporcionado.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Integer userID) {
        boolean confirmation = this.userService.deleteUserById(userID);

        if (confirmation) {
            return new ResponseEntity<>("Se ha eliminado el usuario con id = " + userID, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se eliminó el usuario con id = " + userID, HttpStatus.NOT_FOUND);
        }
    }

}