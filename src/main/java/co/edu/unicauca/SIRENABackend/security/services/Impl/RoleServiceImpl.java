package co.edu.unicauca.SIRENABackend.security.services.Impl;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.unicauca.SIRENABackend.security.common.enums.RoleEnum;
import co.edu.unicauca.SIRENABackend.security.models.RoleModel;
import co.edu.unicauca.SIRENABackend.security.repositories.IRoleRepository;
import co.edu.unicauca.SIRENABackend.security.services.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    IRoleRepository roleRepository;

    @Override
    @Transactional(readOnly = true)
    public ArrayList<RoleModel> getRoles() {
        return (ArrayList<RoleModel>) roleRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RoleModel> getByRoleName(RoleEnum prmRoleName) {
        return roleRepository.findByName(prmRoleName);
    }

    @Override
    @Transactional
    public RoleModel saveRole(RoleModel prmRole) {
        // if (roleRepository.existsByName(prmRole.getName().name())) {
        // throw new RuntimeException("El rol ya existe");
        // }
        return roleRepository.save(prmRole);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RoleModel> getRoleById(Integer prmId) {
        return roleRepository.findById(prmId);
    }
}
