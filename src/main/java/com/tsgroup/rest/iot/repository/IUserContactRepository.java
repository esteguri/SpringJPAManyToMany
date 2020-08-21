package com.tsgroup.rest.iot.repository;

import com.tsgroup.rest.iot.entity.Contact;
import com.tsgroup.rest.iot.entity.User;
import com.tsgroup.rest.iot.entity.UserContact;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IUserContactRepository extends CrudRepository<UserContact, Long> {

    public List<UserContact> findByUser_Id(Long id);
}
