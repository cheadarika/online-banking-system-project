package org.springclass.onlinebankingsystem.service;

import org.springclass.onlinebankingsystem.repository.entity.Role;

import java.util.List;

public interface RoleService {
    Role add(Role role);
    Role update(Long id, Role role);
    List<Role> findAll();
    Role findById(Long id);
}
