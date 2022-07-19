package com.gl.employee.repository;

import com.gl.employee.entity.Role;
import com.gl.employee.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {

    @Query("SELECT r FROM Role r WHERE r.name = ?1")
    public Role findByName(String name);

}
