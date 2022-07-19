package com.gl.employee.service.impl;

import com.gl.employee.entity.Role;
import com.gl.employee.repository.RoleRepository;
import com.gl.employee.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        String roleName = "Role_" + role.getName();
        role.setName(roleName.toUpperCase());
        return roleRepository.save(role);
    }
}
