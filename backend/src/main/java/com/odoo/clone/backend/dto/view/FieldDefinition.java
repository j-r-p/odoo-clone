package com.odoo.clone.backend.dto.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FieldDefinition {
    private String name;
    private String label;
    private String type; // string, integer, boolean, selection, many2one
    private String widget; // input, textarea, select, checkbox
    private boolean required;
    private boolean readOnly;
    private List<Map<String, String>> options; // For selection fields
}