package org.springclass.onlinebankingsystem.controller.request;

import lombok.Value;
import org.springclass.onlinebankingsystem.repository.entity.Role;
import org.springclass.onlinebankingsystem.repository.entity.User;

import java.util.List;

@Value
public class RegisterRequest {
    String username;
    String password;
    String email;
    List<Role> roles;

    public User RegisterUser() {
        var user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.addAllRole(roles);
        return user;
    }
}
