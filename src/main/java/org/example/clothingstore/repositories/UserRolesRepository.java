package org.example.clothingstore.repositories;

import org.example.clothingstore.entities.Role;
import org.example.clothingstore.entities.UserRolesEnum;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRolesRepository {
    Optional<Role> findRoleByName(UserRolesEnum roleName);
    long count();
    void save(Role role);
}
