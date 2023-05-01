package com.iitu.doc.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import com.iitu.doc.models.entity.Role;
import com.iitu.doc.models.entity.User;
import com.iitu.doc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository
                .findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User not found with username : " + username));
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(
                role -> {
                    authorities.add(new SimpleGrantedAuthority(role.getName()));
                }
        );

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);



    }

//        private Set<GrantedAuthority> getSimpleGrantedAuthorities(Role role) {
//        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//
//        grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
//
//        return grantedAuthorities;
//    }
}
