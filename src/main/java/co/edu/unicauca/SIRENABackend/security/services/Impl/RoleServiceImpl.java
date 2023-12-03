package co.edu.unicauca.SIRENABackend.security.services.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.unicauca.SIRENABackend.security.common.enums.RoleEnum;
import co.edu.unicauca.SIRENABackend.security.dtos.request.RoleReq;
import co.edu.unicauca.SIRENABackend.security.dtos.response.RoleRes;
import co.edu.unicauca.SIRENABackend.security.models.RoleModel;
import co.edu.unicauca.SIRENABackend.security.repositories.IRoleRepository;
import co.edu.unicauca.SIRENABackend.security.services.RoleService;

/**
 * proporciona funcionalidades relacionadas con la gestión de roles.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    IRoleRepository roleRepository;

    @Override
    @Transactional(readOnly = true)
    public ArrayList<RoleRes> getRoles() {
        List<RoleModel> roles = roleRepository.findAll();
        ArrayList<RoleRes> rolesRes = new ArrayList<>();
        roles.forEach(role -> {
            rolesRes.add(RoleRes.builder().id(role.getId()).name(role.getName()).build());
        });
        return rolesRes;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RoleRes> getByRoleName(RoleEnum prmRoleName) {
        Optional<RoleModel> role = roleRepository.findByName(prmRoleName);
        if (role.isPresent()) {
            return Optional.of(RoleRes.builder().id(role.get().getId()).name(role.get().getName()).build());
        } else {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public RoleRes saveRole(RoleReq prmRole) {
        RoleModel role = RoleModel.builder().name(prmRole.getName()).build();
        roleRepository.save(role);
        return RoleRes.builder().id(role.getId()).name(role.getName()).build();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RoleRes> getRoleById(Integer prmId) {
        Optional<RoleModel> role = roleRepository.findById(prmId);
        if (role.isPresent()) {
            return Optional.of(RoleRes.builder().id(role.get().getId()).name(role.get().getName()).build());
        } else {
            return Optional.empty();
        }
    }
}
