package com.iitu.doc.models.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@FieldNameConstants
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")
public class User extends AuditableEntity {
    String username;
    String name;
    String password;
    @ManyToMany(fetch = FetchType.EAGER)
    Collection<Role> roles = new ArrayList<>();

}

