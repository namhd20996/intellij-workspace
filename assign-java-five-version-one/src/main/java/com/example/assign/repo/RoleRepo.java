package com.example.assign.repo;

import com.example.assign.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoleRepo extends JpaRepository<Role, UUID> {

    Optional<Role> findRoleByCode(String code);

    List<Role> findRolesByName(String name);
}
