package co.edu.unicauca.SIRENABackend.security.services.Impl;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.unicauca.SIRENABackend.security.dtos.request.UserRegisterReq;
import co.edu.unicauca.SIRENABackend.security.dtos.response.UserRegisterRes;
import co.edu.unicauca.SIRENABackend.security.models.RoleModel;
import co.edu.unicauca.SIRENABackend.security.models.UserModel;
import co.edu.unicauca.SIRENABackend.security.repositories.IRoleRepository;
import co.edu.unicauca.SIRENABackend.security.repositories.ITokenRepository;
import co.edu.unicauca.SIRENABackend.security.repositories.IUserRepository;
import co.edu.unicauca.SIRENABackend.security.services.UserService;
import lombok.RequiredArgsConstructor;

/**
 * Propociona la funcionalidades relacionadas con la gestion de UserService
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ITokenRepository tokenRepository;

    @Override
    @Transactional(readOnly = true)
    public ArrayList<UserRegisterRes> getUsers() {

        ArrayList<UserModel> users = (ArrayList<UserModel>) userRepository.findAll();
        ArrayList<UserRegisterRes> usersRes = new ArrayList<>();

        for (UserModel user : users) {
            var userRes = UserRegisterRes.builder()
                    .usr_id(user.getId())
                    .usr_name(user.getUsername())
                    .usr_firstname(user.getFirstName())
                    .usr_lastname(user.getLastName())
                    .usr_email(user.getEmail())
                    .usr_role(user.getRole().getName())
                    .usr_status(user.getStatus())
                    .build();

            usersRes.add(userRes);
        }

        return usersRes;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserRegisterRes> getByUsername(String prmUsername) {
        var userRes = userRepository.findByUsername(prmUsername).map(user -> UserRegisterRes.builder()
                .usr_id(user.getId())
                .usr_name(user.getUsername())
                .usr_firstname(user.getFirstName())
                .usr_lastname(user.getLastName())
                .usr_email(user.getEmail())
                .usr_role(user.getRole().getName())
                .usr_status(user.getStatus())
                .build());

        return userRes;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByUsername(String prmUsuername) {
        return userRepository.existsByUsername(prmUsuername);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByEmail(String prmEmail) {
        return userRepository.existsByEmail(prmEmail);
    }

    @Override
    @Transactional
    public UserRegisterRes saveUser(UserRegisterReq request) throws RuntimeException {
        if (userRepository.existsByUsername(request.getUsr_name())) {
            return null;
        }
        if (userRepository.existsByEmail(request.getUsr_email())) {
            return null;
        }

        RoleModel role_insert = roleRepository.findByName(request.getUsr_role()).orElseThrow();
        UserModel user = UserModel.builder()
                .role(role_insert)
                .firstName(request.getUsr_firstname())
                .lastName(request.getUsr_lastname())
                .username(request.getUsr_name())
                .password(passwordEncoder.encode(request.getUsr_password()))
                .email(request.getUsr_email())
                .build();

        var savedUser = userRepository.save(user);

        return UserRegisterRes.builder()
                .usr_id(savedUser.getId())
                .usr_name(savedUser.getUsername())
                .usr_firstname(savedUser.getFirstName())
                .usr_lastname(savedUser.getLastName())
                .usr_email(savedUser.getEmail())
                .usr_role(savedUser.getRole().getName())
                .usr_status(savedUser.getStatus())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserRegisterRes> getUserById(Integer prmId) {

        var userRes = userRepository.findById(prmId).map(user -> UserRegisterRes.builder()
                .usr_id(user.getId())
                .usr_name(user.getUsername())
                .usr_firstname(user.getFirstName())
                .usr_lastname(user.getLastName())
                .usr_email(user.getEmail())
                .usr_role(user.getRole().getName())
                .usr_status(user.getStatus())
                .build());

        return userRes;
    }

    @Override
    @Transactional
    public boolean deactivateUser(Integer prmId) {
        Optional<UserModel> user = userRepository.findById(prmId);
        if (user.isPresent()) {
            user.get().setStatus(false);
            userRepository.save(user.get());
            revokeAllUserTokens(user.get());
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean activateUser(Integer prmId) {
        Optional<UserModel> user = userRepository.findById(prmId);
        if (user.isPresent() && user.get().getRole().getName().name() != "ADMIN") {
            user.get().setStatus(true);
            userRepository.save(user.get());
            return true;
        }
        return false;
    }

    public void revokeAllUserTokens(UserModel prmUser) {
        var validUserToken = tokenRepository.findAllValidTokensByUser(prmUser.getId());
        if (validUserToken.isEmpty())
            return;
        validUserToken.forEach(Token -> {
            Token.setExpired(true);
            Token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserToken);
    }
}