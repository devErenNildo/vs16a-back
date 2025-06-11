package com.erenildo.fakebank.auth.repository;

import com.erenildo.fakebank.auth.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleId(long roleId);
}
