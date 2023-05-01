package com.iitu.doc.models.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "Dto for user registration")
public class UserRegisterDto {
    @Schema(description = "User username", example = "27488")
    @NotBlank(message = "Username field required")
    String username;

    @Schema(description = "User firstname", example = "Marzhan")
    @NotBlank(message = "User firstname field required")
    @Size(max = 50, message = "User firstname must be no more 50 characters")
    String name;

    @Schema(description = "User's password", example = "qwert123")
    @Size(min = 8, max = 40, message = "Password length must be minimum 8, maximum 40")
    String password;

    @Schema(description = "Roles of user", example = "USER")
    @NotNull(message = "Role field required")
    String roles;
}
