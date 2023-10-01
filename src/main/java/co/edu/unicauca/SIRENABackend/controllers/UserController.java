package co.edu.unicauca.SIRENABackend.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import co.edu.unicauca.SIRENABackend.models.UserModel;
import co.edu.unicauca.SIRENABackend.services.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * Obtiene una lista de todos los usuarios registrados.
     *
     * @return Una lista de objetos UserModel que representan a todos los usuarios.
     */
    @GetMapping()
    public ArrayList<UserModel> getUsers() {
        return userService.getUsers();
    }

    /**
     * Guarda un nuevo usuario.
     *
     * @param prmUser El objeto UserModel que se desea guardar.
     * @return El objeto UserModel guardado en la base de datos.
     */
    @PostMapping()
    public UserModel saveUser(@RequestBody UserModel prmUser) {
        return this.userService.saveUser(prmUser);
    }

    /**
     * Obtiene un usuario por su ID.
     *
     * @param userID El identificador único del usuario que se desea obtener.
     * @return Un objeto Optional que contiene el usuario si se encuentra, o vacío si no se encuentra.
     */
    @GetMapping(path = "/{id}")
    public Optional<UserModel> getUserById(@PathVariable("id") Integer userID) {
        return this.userService.getUserById(userID);
    }

    /**
     * Elimina un usuario por su ID.
     *
     * @param userID El identificador único del usuario que se desea eliminar.
     * @return Un mensaje que indica si se eliminó o no el usuario con el ID proporcionado.
     */
    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Integer userID) {
        boolean confirmation = this.userService.deleteUserById(userID);
        if (confirmation) {
            return "Se ha eliminado el usuario con id = " + userID;
        } else {
            return "No se eliminó el usuario con id = " + userID;
        }
    }
}