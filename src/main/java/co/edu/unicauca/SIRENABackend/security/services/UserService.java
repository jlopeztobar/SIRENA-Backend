package co.edu.unicauca.SIRENABackend.security.services;

import java.util.ArrayList;
import java.util.Optional;

import co.edu.unicauca.SIRENABackend.security.dtos.request.UserRegisterReq;
import co.edu.unicauca.SIRENABackend.security.dtos.response.UserRes;
import co.edu.unicauca.SIRENABackend.security.models.UserModel;

public interface UserService {
    ArrayList<UserRes> getUsers();

    Optional<UserModel> getByUsername(String prmUsername);

    boolean existsByUsername(String prmUsername);

    boolean existsByEmail(String prmEmail);

    UserRes saveUser(UserRegisterReq prmUser);

    Optional<UserRes> getUserById(Integer prmId);

    boolean deleteUserById(Integer prmId);
}
