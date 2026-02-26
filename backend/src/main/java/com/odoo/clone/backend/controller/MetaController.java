package com.odoo.clone.backend.controller;

import com.odoo.clone.backend.dto.view.FieldDefinition;
import com.odoo.clone.backend.dto.view.ViewDefinition;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/meta")
public class MetaController {

    @GetMapping("/{model}/{viewType}")
    public ResponseEntity<ViewDefinition> getView(@PathVariable String model, @PathVariable String viewType) {
        if ("user".equals(model) && "form".equals(viewType)) {
            return ResponseEntity.ok(ViewDefinition.builder()
                    .model("user")
                    .type("form")
                    .title("User Form")
                    .fields(List.of(
                            FieldDefinition.builder()
                                    .name("username")
                                    .label("Username")
                                    .type("string")
                                    .widget("input")
                                    .required(true)
                                    .build(),
                            FieldDefinition.builder()
                                    .name("email")
                                    .label("Email")
                                    .type("string")
                                    .widget("input")
                                    .required(true)
                                    .build(),
                            FieldDefinition.builder()
                                    .name("role")
                                    .label("Role")
                                    .type("selection")
                                    .widget("select")
                                    .options(List.of(
                                            Map.of("value", "ADMIN", "label", "Administrator"),
                                            Map.of("value", "USER", "label", "User")
                                    ))
                                    .build()
                    ))
                    .build());
        }
        return ResponseEntity.notFound().build();
    }
}