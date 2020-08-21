package com.tsgroup.rest.iot.service;

import com.tsgroup.rest.iot.entity.User;

import java.util.List;

public interface IUserService {

    public List<User> findAll();

    public User findById(Long id);

    public User findByDocument(String document);

    public User save(User user);
}
