package com.gl.employee.controller;

import com.gl.employee.entity.Role;
import com.gl.employee.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping(value = "/roles/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        return new ResponseEntity(roleService.save(role), HttpStatus.CREATED);
    }
}
