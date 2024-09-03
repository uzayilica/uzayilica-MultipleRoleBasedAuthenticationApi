package com.uzay.multiplerolebasedauthenticationapia.repository;


import com.uzay.multiplerolebasedauthenticationapia.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Roles, Long> {
    Optional<Roles> findByRoleName(String roleName);
}
