package com.odoo.clone.backend;

import com.odoo.clone.backend.model.Permission;
import com.odoo.clone.backend.model.Role;
import com.odoo.clone.backend.model.User;
import com.odoo.clone.backend.repository.PermissionRepository;
import com.odoo.clone.backend.repository.RoleRepository;
import com.odoo.clone.backend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class UserIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Test
    @Transactional
    void shouldCreateUserWithRolesAndPermissions() {
        // Create Permission
        Permission readPermission = new Permission();
        readPermission.setName("READ_USER");
        readPermission.setDescription("Can read users");
        readPermission.setCreatedAt(LocalDateTime.now());
        readPermission.setUpdatedAt(LocalDateTime.now());
        permissionRepository.save(readPermission);

        // Create Role
        Role adminRole = new Role();
        adminRole.setName("ADMIN");
        adminRole.setDescription("Administrator");
        adminRole.setPermissions(Set.of(readPermission));
        adminRole.setCreatedAt(LocalDateTime.now());
        adminRole.setUpdatedAt(LocalDateTime.now());
        roleRepository.save(adminRole);

        // Create User
        User user = new User();
        user.setUsername("jdoe");
        user.setEmail("jdoe@example.com");
        user.setPassword("secret");
        user.setRoles(Set.of(adminRole));
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        
        // Test Custom Attributes (JSONB)
        user.setCustomAttributes(Map.of("department", "IT", "level", 5));
        
        userRepository.save(user);

        // Verify
        User savedUser = userRepository.findByUsername("jdoe").orElseThrow();
        assertThat(savedUser.getEmail()).isEqualTo("jdoe@example.com");
        assertThat(savedUser.getRoles()).hasSize(1);
        assertThat(savedUser.getRoles().iterator().next().getName()).isEqualTo("ADMIN");
        assertThat(savedUser.getRoles().iterator().next().getPermissions()).hasSize(1);
        assertThat(savedUser.getRoles().iterator().next().getPermissions().iterator().next().getName()).isEqualTo("READ_USER");
        
        // Verify JSONB
        assertThat(savedUser.getCustomAttributes()).containsEntry("department", "IT");
        assertThat(savedUser.getCustomAttributes()).containsEntry("level", 5);
    }
}