package com.tsgroup.rest.iot.serviceimpl;

import com.tsgroup.rest.iot.entity.User;
import com.tsgroup.rest.iot.repository.IUserRespository;
import com.tsgroup.rest.iot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRespository iUserRespository;

    @Override
    public List<User> findAll() {
        return (List<User>) iUserRespository.findAll();
    }

    @Override
    public User findById(Long id) {
        return iUserRespository.findById(id).orElse(null);
    }

    @Override
    public User save(User user) {
        return iUserRespository.save(user);
    }

    @Override
    public User findByDocument(String document) {
        return iUserRespository.findByDocument(document);
    }
}
