package com.odoo.clone.backend.core.service;

import com.odoo.clone.backend.core.model.View;
import com.odoo.clone.backend.core.repository.ViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ViewService {

    private final ViewRepository viewRepository;

    @Transactional(readOnly = true)
    public Map<String, Object> getView(String model, String type) {
        return viewRepository.findByModelAndType(model, type)
                .map(View::getArchitecture)
                .orElseThrow(() -> new RuntimeException("View not found for model: " + model + " and type: " + type));
    }

    @Transactional
    public View createView(String name, String model, String type, Map<String, Object> architecture) {
        View view = new View();
        view.setName(name);
        view.setModel(model);
        view.setType(type);
        view.setArchitecture(architecture);
        return viewRepository.save(view);
    }
}
