package org.springclass.onlinebankingsystem.service.implement;

import org.springclass.onlinebankingsystem.exception.CustomException;
import org.springclass.onlinebankingsystem.repository.UserRepository;
import org.springclass.onlinebankingsystem.repository.entity.User;
import org.springclass.onlinebankingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsernameOrEmail(username).orElseThrow(() ->
                new CustomException(404, "Username or email does not exist!!!"));
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new CustomException(404, "User not found!"));
    }

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }
}