package com.tsgroup.rest.iot.serviceimpl;

import com.tsgroup.rest.iot.entity.Contact;
import com.tsgroup.rest.iot.entity.UserContact;
import com.tsgroup.rest.iot.repository.IUserContactRepository;
import com.tsgroup.rest.iot.service.IUserContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserContactServiceImpl implements IUserContactService {

    @Autowired
    private IUserContactRepository iUserContactRepository;

    @Override
    public List<Contact> findContactsForUser(Long id) {
        List<UserContact> userContacts = (List<UserContact>) iUserContactRepository.findByUser_Id(id);
        List<Contact> contacts = new ArrayList<>();
        for (UserContact userContact : userContacts){
            contacts.add(userContact.getContact());
        }
        return contacts;
    }

    @Override
    public UserContact save(UserContact userContact) {
        return iUserContactRepository.save(userContact);
    }
}
