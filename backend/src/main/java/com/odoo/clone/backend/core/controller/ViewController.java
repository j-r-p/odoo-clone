package com.odoo.clone.backend.core.controller;

import com.odoo.clone.backend.core.service.ViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/views")
@RequiredArgsConstructor
public class ViewController {

    private final ViewService viewService;

    @GetMapping("/{model}/{type}")
    public ResponseEntity<Map<String, Object>> getView(@PathVariable String model, @PathVariable String type) {
        return ResponseEntity.ok(viewService.getView(model, type));
    }

    @PostMapping
    public ResponseEntity<?> createView(@RequestBody Map<String, Object> viewData) {
        viewService.createView(
                (String) viewData.get("name"),
                (String) viewData.get("model"),
                (String) viewData.get("type"),
                (Map<String, Object>) viewData.get("architecture")
        );
        return ResponseEntity.ok("View created successfully!");
    }
}
