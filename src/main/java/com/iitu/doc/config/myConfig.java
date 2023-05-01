package com.iitu.doc.config;

import com.iitu.doc.models.entity.Role;
import com.iitu.doc.models.entity.User;
import com.iitu.doc.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@Configuration
public class myConfig {
    @Bean
    CommandLineRunner commandLineRunner(UserService userService){
        return args -> {
            userService.saveRole(new Role("USER"));
            userService.saveRole(new Role("MODERATOR"));
            userService.saveRole(new Role("ADMIN"));

            userService.saveUser(new User("ADMIN","admin","ADMIN", new ArrayList<>()));
            userService.saveUser(new User("USER","user","USER", new ArrayList<>()));
            userService.saveUser(new User("MODERATOR","moderator","MODERATOR", new ArrayList<>()));

            userService.assignRole("USER","USER");
            userService.assignRole("MODERATOR","MODERATOR");
            userService.assignRole("ADMIN","ADMIN");
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
