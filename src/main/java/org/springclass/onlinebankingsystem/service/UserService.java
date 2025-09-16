package org.springclass.onlinebankingsystem.service;

import org.springclass.onlinebankingsystem.controller.request.UserRoleRequest;
import org.springclass.onlinebankingsystem.controller.response.UserResponse;
import org.springclass.onlinebankingsystem.repository.entity.User;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface UserService {
    Page<UserResponse> findAll(Optional<String> query, int page, int size);
    User find(Long id);
    void createUser(User user);
    User update(User user);
    void delete(Long id);
    User getUserInfo();
    User findUserEnabled(String username);
    User assignRole(UserRoleRequest request);
}
