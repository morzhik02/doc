package com.iitu.doc.service.impl;

import com.iitu.doc.exceptions.DiplomaCoreException;
import com.iitu.doc.models.constants.ApiMessages;
import com.iitu.doc.models.entity.Role;
import com.iitu.doc.models.entity.User;
import com.iitu.doc.repository.RoleRepository;
import com.iitu.doc.repository.UserRepository;
import com.iitu.doc.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService{

    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        log.info("Saving new user {} to the database", user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database", role.getName());
        return roleRepository.save(role);
    }

//    @Override
//    public void assignRole(String username, String roleName) {
//        log.info("Saving role {} to user {}", roleName, username);
//        User user = userRepository.findByUsername(username);
//        Role role = roleRepository.findByName(roleName);
//        user.getRoles().add(role);
//    }

//    @Override
//    public Optional<User> getUser(String username) {
//        log.info("Fetching user {}", username);
//        return userRepository.findByUsername(username);
//    }

//    @Override
//    public List<User> getUsers() {
//        log.info("Fetching all users");
//        return userRepository.findAll();
//    }

    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new DiplomaCoreException(HttpStatus.BAD_REQUEST, ApiMessages.USER_NOT_FOUND,
                        "User with this username not found"));
    }

//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username);
//
//        if(user == null){
//            log.error("User not found with username: {}", username);
//            throw new UsernameNotFoundException("User not found with username:" + username);
//        } else {
//            log.info("User foundUser not found with username: {}", username);
//        }
//
//        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        user.getRoles().forEach(
//                role -> {
//                    authorities.add(new SimpleGrantedAuthority(role.getName()));
//                }
//        );
//
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
//    }
}
