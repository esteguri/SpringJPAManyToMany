package com.tsgroup.rest.iot.repository;

import com.tsgroup.rest.iot.entity.Contact;
import org.springframework.data.repository.CrudRepository;

public interface IContactRepository extends CrudRepository<Contact, Long> {

    public Contact findByDocument(String document);

}
