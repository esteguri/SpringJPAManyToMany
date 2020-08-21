package com.tsgroup.rest.iot.repository;

import com.tsgroup.rest.iot.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface IUserRespository extends CrudRepository<User, Long> {

    public User findByDocument(String document);

}
