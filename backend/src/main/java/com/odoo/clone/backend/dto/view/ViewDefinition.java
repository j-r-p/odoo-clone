package com.odoo.clone.backend.dto.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ViewDefinition {
    private String model;
    private String type; // form, tree, kanban
    private String title;
    private List<FieldDefinition> fields;
}