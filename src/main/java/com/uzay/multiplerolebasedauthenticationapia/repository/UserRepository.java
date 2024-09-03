package com.uzay.multiplerolebasedauthenticationapia.repository;

import com.uzay.multiplerolebasedauthenticationapia.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Long> {
    Optional<Users> findByUsername(String username);
    void deleteByUsername(String username);

}
