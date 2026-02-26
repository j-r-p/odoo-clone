package com.odoo.clone.backend.core.repository;

import com.odoo.clone.backend.core.model.Contact;
import com.odoo.clone.backend.core.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, UUID> {
    Optional<Permission> findByName(String name);
}
