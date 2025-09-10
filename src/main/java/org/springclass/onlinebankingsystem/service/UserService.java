package org.springclass.onlinebankingsystem.service;

import org.springclass.onlinebankingsystem.repository.entity.User;

public interface UserService {
    User getUserById(Long id);
    void createUser(User user);
}
