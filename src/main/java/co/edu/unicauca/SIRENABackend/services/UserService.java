package co.edu.unicauca.SIRENABackend.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.unicauca.SIRENABackend.models.UserModel;
import co.edu.unicauca.SIRENABackend.repositories.IUserRepository;

/**
 * Servicio para gestionar usuarios.
 */
@Service
public class UserService {

    @Autowired
    private IUserRepository userRepository;

    /**
     * Obtiene una lista de todos los usuarios registrados.
     *
     * @return Una lista de objetos UserModel que representan a todos los usuarios.
     */
    @Transactional(readOnly = true)
    public ArrayList<UserModel> getUsers() {
        return (ArrayList<UserModel>) userRepository.findAll();
    }

    /**
     * Guarda un nuevo usuario en la base de datos.
     *
     * @param prmUser El objeto UserModel que se desea guardar.
     * @return La instancia de UserModel guardada en la base de datos.
     */
    @Transactional
    public UserModel saveUser(UserModel prmUser) {
        return userRepository.save(prmUser);
    }

    /**
     * Obtiene un usuario por su identificador único.
     *
     * @param prmId El identificador único del usuario que se desea obtener.
     * @return Un objeto Optional que contiene el usuario si se encuentra, o vacío si no se encuentra.
     */
    @Transactional(readOnly = true)
    public Optional<UserModel> getUserById(Integer prmId) {
        return userRepository.findById(prmId);
    }

    /**
     * Elimina un usuario por su identificador único.
     *
     * @param prmId El identificador único del usuario que se desea eliminar.
     * @return `true` si el usuario se eliminó con éxito, `false` si no se pudo eliminar.
     */
    @Transactional
    public boolean deleteUserById(Integer prmId) {
        try {
            userRepository.deleteById(prmId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}