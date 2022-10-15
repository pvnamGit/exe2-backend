package com.motorbikes_hiring.repository.role;

import com.motorbikes_hiring.model.role.ERole;
import com.motorbikes_hiring.model.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByUserRole(ERole userRole);
}
