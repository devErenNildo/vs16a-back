package com.erenildo.fakebank.repository;

import com.erenildo.fakebank.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
    Role findByRoleId(Long roleId);
}
