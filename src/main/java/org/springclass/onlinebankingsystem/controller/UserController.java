package org.springclass.onlinebankingsystem.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springclass.onlinebankingsystem.controller.response.UserResponse;
import org.springclass.onlinebankingsystem.repository.entity.Role;
import org.springclass.onlinebankingsystem.service.RoleService;
import org.springclass.onlinebankingsystem.service.UserService;
import org.springclass.onlinebankingsystem.shared.object.ResponseObjectMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final RoleService roleService;
    private final ResponseObjectMap responseObjectMap;

    @GetMapping("{id}")
    public Map<String, Object> getById(@PathVariable Long id) {
        return responseObjectMap.responseObject(new UserResponse(service.getUserById(id)));
    }

    @PostMapping("/add-role")
    public Map<String, Object> addRole(@RequestBody Role role) {
        return responseObjectMap.responseObject(roleService.add(role));
    }
}
