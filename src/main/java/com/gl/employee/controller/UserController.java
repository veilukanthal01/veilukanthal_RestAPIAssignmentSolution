package com.gl.employee.controller;

import com.gl.employee.entity.Role;
import com.gl.employee.entity.User;
import com.gl.employee.service.RoleService;
import com.gl.employee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/users/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Role> registerUser(@RequestBody User user) {
        return new ResponseEntity(userService.registerUser(user), HttpStatus.CREATED);
    }
}
