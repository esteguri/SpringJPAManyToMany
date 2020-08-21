package com.tsgroup.rest.iot.service;

import com.tsgroup.rest.iot.entity.Contact;
import com.tsgroup.rest.iot.entity.UserContact;

import java.util.List;

public interface IUserContactService {

    public UserContact save(UserContact userContact);

    public List<Contact> findContactsForUser(Long id);

}
