package co.edu.unicauca.SIRENABackend.security.services;

import java.util.ArrayList;
import java.util.Optional;

import co.edu.unicauca.SIRENABackend.security.dtos.request.UserRegisterReq;
import co.edu.unicauca.SIRENABackend.security.dtos.response.UserRegisterRes;

/**
 * Servicio que proporciona operaciones relacionadas con usuarios en la aplicación.
 */
public interface UserService {

    /**
     * Obtiene la lista de usuarios registrados en la aplicación.
     *
     * @return Lista de usuarios representados como objetos {@code UserRegisterRes}.
     */
    ArrayList<UserRegisterRes> getUsers();

    /**
     * Obtiene un usuario por su nombre de usuario.
     *
     * @param prmUsername Nombre de usuario del usuario.
     * @return Un objeto {@code UserRegisterRes} si el usuario existe, de lo contrario, un valor opcional vacío.
     */
    Optional<UserRegisterRes> getByUsername(String prmUsername);

    /**
     * Verifica si ya existe un usuario con el nombre de usuario proporcionado.
     *
     * @param prmUsername Nombre de usuario a verificar.
     * @return {@code true} si el nombre de usuario ya está en uso, de lo contrario, {@code false}.
     */
    boolean existsByUsername(String prmUsername);

    /**
     * Verifica si ya existe un usuario con el correo electrónico proporcionado.
     *
     * @param prmEmail Correo electrónico a verificar.
     * @return {@code true} si el correo electrónico ya está en uso, de lo contrario, {@code false}.
     */
    boolean existsByEmail(String prmEmail);

    /**
     * Guarda un nuevo usuario en la aplicación.
     *
     * @param prmUser Objeto de solicitud que contiene la información del nuevo usuario.
     * @return Objeto {@code UserRegisterRes} que representa el usuario guardado.
     */
    UserRegisterRes saveUser(UserRegisterReq prmUser);


    /**
     * Obtiene un usuario por su identificador único.
     *
     * @param prmId Identificador único del usuario.
     * @return Un objeto {@code UserRegisterRes} si el usuario existe, de lo contrario, un valor opcional vacío.
     */
    Optional<UserRegisterRes> getUserById(Integer prmId);

    /**
     * Desactiva un usuario en la aplicación.
     *
     * @param prmId Identificador único del usuario a desactivar.
     * @return {@code true} si el usuario fue desactivado correctamente, de lo contrario, {@code false}.
     */
    boolean deactivateUser(Integer prmId);

    /**
     * Activa un usuario en la aplicación.
     *
     * @param prmId Identificador único del usuario a activar.
     * @return {@code true} si el usuario fue activado correctamente, de lo contrario, {@code false}.
     */
    boolean activateUser(Integer prmId);
}
