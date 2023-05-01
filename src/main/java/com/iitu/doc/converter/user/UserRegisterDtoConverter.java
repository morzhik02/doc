package com.iitu.doc.converter.user;


import com.iitu.doc.models.dto.UserRegisterDto;
import com.iitu.doc.models.entity.Role;
import com.iitu.doc.models.entity.User;
import com.iitu.doc.repository.RoleRepository;
import com.iitu.doc.util.converter.AbstractConverter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserRegisterDtoConverter extends AbstractConverter<UserRegisterDto, User> {

    RoleRepository roleRepository;

    PasswordEncoder passwordEncoder;

    @Override
    public void fill(UserRegisterDto source, User target) {
        target.setName(source.getName());
        target.setUsername(source.getUsername());

        target.setPassword(passwordEncoder.encode(source.getPassword()));
        target.setRoles((Collection<Role>) roleRepository.findByName(source.getRoles()));
    }
}
