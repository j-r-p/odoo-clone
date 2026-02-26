package com.odoo.clone.backend.core.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.odoo.clone.backend.BaseIntegrationTest;
import com.odoo.clone.backend.core.model.Contact;
import com.odoo.clone.backend.core.repository.ContactRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ContactControllerTest extends BaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser
    void shouldCreateAndGetContact() throws Exception {
        Contact contact = new Contact();
        contact.setName("Test Contact");
        contact.setEmail("test@example.com");
        contact.setPhone("1234567890");
        contact.setAddress("123 Test St");

        mockMvc.perform(post("/api/contacts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(contact)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/contacts"))
                .andExpect(status().isOk());
    }
}
