package com.tsgroup.rest.iot.service;

import com.tsgroup.rest.iot.entity.Contact;

import java.util.List;

public interface IContactService {

    public List<Contact> findAll();

    public Contact findById(Long id);

    public Contact findByDocument(String document);

    public Contact save(Contact contact);

}
