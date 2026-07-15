package com.example.securityApp.securityApplicationo.dto;

import com.example.securityApp.securityApplicationo.entities.enums.Permission;
import com.example.securityApp.securityApplicationo.entities.enums.Role;
import lombok.Data;

import java.util.Set;

@Data
public class SignUpDTO {

    private String email;
    private String password;
    private String name;

    private Set<Role> roles;
    private Set<Permission> permissions;
}
