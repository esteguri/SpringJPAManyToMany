package com.tsgroup.rest.iot.controller;

import com.tsgroup.rest.iot.entity.Contact;
import com.tsgroup.rest.iot.entity.User;
import com.tsgroup.rest.iot.entity.UserContact;
import com.tsgroup.rest.iot.service.IUserContactService;
import com.tsgroup.rest.iot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private IUserContactService iUserContactService;

    @GetMapping("")
    public ResponseEntity<Map<String, Object>> findAll(){
        Map<String, Object> response = new HashMap<>();
        List<User> users = iUserService.findAll();
        response.put("ok", true);
        response.put("users", users);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        User user = null;
        try {
            user = iUserService.findById(id);
        }catch (DataAccessException e){
            response.put("ok", false);
            response.put("error", e.getMessage());
            response.put("message", "Error al consultar el usuario");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (user == null){
            response.put("ok", false);
            response.put("message", "El usuario con id " + id + " no existe");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        response.put("ok", true);
        response.put("user", user);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @GetMapping("/findByDocument/{document}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable String document){
        Map<String, Object> response = new HashMap<>();
        User user = null;
        try {
            user = iUserService.findByDocument(document);
        }catch (DataAccessException e){
            response.put("ok", false);
            response.put("error", e.getMessage());
            response.put("message", "Error al consultar el usuario");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (user == null){
            response.put("ok", false);
            response.put("message", "El usuario con documento " + document + " no existe");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        response.put("ok", true);
        response.put("user", user);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}/contacts")
    public ResponseEntity<Map<String, Object>> findContacts(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        User user = iUserService.findById(id);

        if(user == null){
            response.put("ok", false);
            response.put("message", "El usuario con id " + id + " no existe");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        List<Contact> userContacts = iUserContactService.findContactsForUser(id);

        response.put("ok", true);
        response.put("user", user);
        response.put("contacts", userContacts);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Map<String, Object>> save(@RequestBody User user){
        Map<String, Object> response = new HashMap<>();
        User newUser = null;
        try {
            newUser = iUserService.save(user);
        }catch (DataAccessException e){
            response.put("ok", false);
            response.put("error", e.getMessage());
            response.put("message", "Error al guardar el usuario");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "Usuario creado correctamente");
        response.put("user", newUser );
        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED );
    }


}
