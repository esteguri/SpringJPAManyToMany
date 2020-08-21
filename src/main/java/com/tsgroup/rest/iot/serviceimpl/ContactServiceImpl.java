package com.tsgroup.rest.iot.serviceimpl;

import com.tsgroup.rest.iot.entity.Contact;
import com.tsgroup.rest.iot.repository.IContactRepository;
import com.tsgroup.rest.iot.service.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements IContactService {

    @Autowired
    private IContactRepository iContactRepository;

    @Override
    public List<Contact> findAll() {
        return (List<Contact>) iContactRepository.findAll();
    }

    @Override
    public Contact findById(Long id) {
        return iContactRepository.findById(id).orElse(null);
    }

    @Override
    public Contact save(Contact contact) {
        return iContactRepository.save(contact);
    }

    @Override
    public Contact findByDocument(String document) {
        return iContactRepository.findByDocument(document);
    }
}
