package com.odoo.clone.backend.core.repository;

import com.odoo.clone.backend.core.model.View;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ViewRepository extends JpaRepository<View, Long> {
    Optional<View> findByModelAndType(String model, String type);
}
