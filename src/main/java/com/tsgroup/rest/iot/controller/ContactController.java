package com.tsgroup.rest.iot.controller;

import com.tsgroup.rest.iot.entity.Contact;
import com.tsgroup.rest.iot.entity.User;
import com.tsgroup.rest.iot.entity.UserContact;
import com.tsgroup.rest.iot.service.IContactService;
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
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private IContactService iContactService;
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IUserContactService iUserContactService;

    @GetMapping("")
    public ResponseEntity<Map<String, Object>> findAll(){
        Map<String, Object> response = new HashMap<>();
        List<Contact> contacts = iContactService.findAll();
        response.put("ok", true);
        response.put("contacts", contacts);
        response.put("total", contacts.size());
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        Contact contact = null;
        try {
            contact = iContactService.findById(id);
        }catch (DataAccessException e){
            response.put("ok", false);
            response.put("error", e.getMessage());
            response.put("message", "Error al consultar el contacto");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (contact == null){
            response.put("ok", false);
            response.put("message", "El contacto con id " + id + " no existe");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        response.put("ok", true);
        response.put("contact", contact);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

    }

    @PostMapping("")
    public ResponseEntity<Map<String, Object>> save(@RequestBody Contact contact){
        Map<String, Object> response = new HashMap<>();
        Contact newContact = null;
        try {
            newContact = iContactService.save(contact);
        }catch (DataAccessException e){
            response.put("ok", false);
            response.put("error", e.getMessage());
            response.put("message", "Error al guardar el contacto");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "Contacto creado correctamente");
        response.put("contact", newContact );
        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED );

    }

    @PostMapping("/{document}/users/{id}")
    public ResponseEntity<Map<String, Object>> saveContact(@PathVariable String document,
                                                           @PathVariable Long id){

        Map<String, Object> response = new HashMap<>();
        Contact contact = iContactService.findByDocument(document);
        User user = iUserService.findById(id);

        if (contact == null){
           response.put("ok", false);
           response.put("message", "El contacto con documento " + document + " no existe");
           return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }


        if (user == null){
            response.put("ok", false);
            response.put("message", "El usuario con id " + id + " no existe");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        UserContact userContact = iUserContactService.save(new UserContact(user, contact));
        response.put("userContact", userContact);
        response.put("ok", true);

        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED );
    }

}
