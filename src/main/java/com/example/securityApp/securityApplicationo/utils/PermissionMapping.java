package com.example.securityApp.securityApplicationo.utils;

import com.example.securityApp.securityApplicationo.entities.User;
import com.example.securityApp.securityApplicationo.entities.enums.Permission;
import com.example.securityApp.securityApplicationo.entities.enums.Role;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.securityApp.securityApplicationo.entities.enums.Permission.*;
import static com.example.securityApp.securityApplicationo.entities.enums.Permission.*;
import static com.example.securityApp.securityApplicationo.entities.enums.Role.*;

public class PermissionMapping {

    public static final Map<Role, Set<Permission>> map = Map.of(
            USER, Set.of(USER_VIEW, POST_VIEW),
            CREATOR, Set.of(POST_CREATE, USER_UPDATE, POST_UPDATE),
            ADMIN, Set.of(POST_CREATE, USER_UPDATE, POST_UPDATE, USER_DELETE, USER_CREATE, POST_DELETE)
    );

    public static Set<SimpleGrantedAuthority> grantedAuthoritiesForRole(Role role){
        return map.get(role).stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toSet());
    }
}
