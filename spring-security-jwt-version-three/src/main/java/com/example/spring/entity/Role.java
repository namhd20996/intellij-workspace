package com.example.spring.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public enum Role {
    //    USER(Collections.emptySet()),
    USER(Set.of(Permission.ADMIN_READ)),
    ADMIN(
            Set.of(
                    Permission.ADMIN_READ,
                    Permission.ADMIN_CREATE,
                    Permission.ADMIN_UPDATE,
                    Permission.ADMIN_DELETE,
                    Permission.MANAGER_READ,
                    Permission.MANAGER_CREATE,
                    Permission.MANAGER_UPDATE,
                    Permission.MANAGER_DELETE
            )
    ),
    ADMIN_FAKE(
            Set.of(
                    Permission.ADMIN_READ,
                    Permission.MANAGER_READ,
                    Permission.MANAGER_CREATE,
                    Permission.MANAGER_UPDATE,
                    Permission.MANAGER_DELETE
            )
    ),
    MANAGER(
            Set.of(
                    Permission.MANAGER_READ,
                    Permission.MANAGER_CREATE,
                    Permission.MANAGER_UPDATE,
                    Permission.MANAGER_DELETE
            )
    );

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        // Lấy tất cả value của Role để truyền vào List và cuối List Add thêm tên của Role đó
        var authorities = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
