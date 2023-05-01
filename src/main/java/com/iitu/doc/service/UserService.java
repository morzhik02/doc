package com.iitu.doc.service;

import com.iitu.doc.models.entity.Role;
import com.iitu.doc.models.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void assignRole(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();
}
