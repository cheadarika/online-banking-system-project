package org.springclass.onlinebankingsystem.controller;

import lombok.extern.slf4j.Slf4j;
import org.springclass.onlinebankingsystem.controller.response.UserResponse;
import org.springclass.onlinebankingsystem.repository.entity.Role;
import org.springclass.onlinebankingsystem.service.RoleService;
import org.springclass.onlinebankingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private RoleService roleService;

    @GetMapping("{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(new UserResponse(service.getUserById(id)));
    }

    @PostMapping("/add-role")
    public ResponseEntity<Role> addRole(@RequestBody Role role) {
        return ResponseEntity.ok(roleService.add(role));
    }
}
