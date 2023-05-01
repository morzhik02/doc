package com.iitu.doc.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
//@Schema(description = "Dto for user update")
public class RoleToUserForm {
//    @Schema(description = "User firstname", example = "Marzhan")
//    @Size(max = 50, message = "User firstname must be no more 50 characters")
    String username;

//    @Schema(description = "User lastname", example = "Izteleu")
//    @Size(max = 50, message = "User lastname must be no more 50 characters")
    String roleName;
}
