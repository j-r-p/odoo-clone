package com.odoo.clone.backend.core.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.odoo.clone.backend.BaseIntegrationTest;
import com.odoo.clone.backend.core.repository.ViewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ViewControllerTest extends BaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ViewRepository viewRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser
    void shouldCreateAndGetView() throws Exception {
        Map<String, Object> viewData = new HashMap<>();
        viewData.put("name", "Test View");
        viewData.put("model", "test.model");
        viewData.put("type", "form");
        viewData.put("architecture", new HashMap<>());

        mockMvc.perform(post("/api/views")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(viewData)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/views/test.model/form"))
                .andExpect(status().isOk());
    }
}
