package com.iitu.doc.service;

import com.iitu.doc.models.entity.Role;
import com.iitu.doc.models.entity.User;

import java.util.Optional;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
//    void assignRole(String username, String roleName);
//    void assignRole(UserRegisterDto userRegisterDto);
//    Optional<User> getUser(String username);

    User findByUsername(String username);
}
