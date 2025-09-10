package org.springclass.onlinebankingsystem.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springclass.onlinebankingsystem.base.entity.BaseEntity;
import org.springclass.onlinebankingsystem.repository.entity.enumerate.Gender;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {

    @Getter
    @Column(unique = true, nullable = false)
    private String username;

    @Getter
    @Column(nullable = false)
    private String password;

    @Getter
    @Column(nullable = false)
    private String email;

    private String firstName;
    private String lastName;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles", // join table
            joinColumns = @JoinColumn(name = "user_id"), // FK to User
            inverseJoinColumns = @JoinColumn(name = "role_id") // FK to Role
    )
    private Set<Role> roles = new HashSet<>();

    // Constructors
    public User() {}

    public User(String username, String password, String email,  Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    // UserDetails implementation
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + roles.stream().findFirst().get().getRole().name()));
    }

    public List<Role> getRoles() {
        return this.roles.stream().toList();
    }

    // helper methods
    public void addAllRole(List<Role> roles) {
        this.roles.addAll(roles);
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public void removeRole(Role role) {
        roles.remove(role);
    }
}