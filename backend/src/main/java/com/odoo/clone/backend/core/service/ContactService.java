package com.odoo.clone.backend.core.service;

import com.odoo.clone.backend.core.model.Contact;
import com.odoo.clone.backend.core.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;

    @Transactional(readOnly = true)
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @Transactional
    public Contact createContact(Contact contact) {
        return contactRepository.save(contact);
    }
}
