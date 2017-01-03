//package com.zombiesurvival.services;
//
//
//import com.springbootwebapp.domain.Contact;
//import com.springbootwebapp.repositories.ContactRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
///**
// * Created by bru9isk on 01/10/16.
// */
//@Service
//public class ContactServiceImpl implements ContactService {
//
//    private ContactRepository contactRepository;
//
//    @Autowired
//    public void setContactRepository(ContactRepository contactRepository) {
//        this.contactRepository = contactRepository;
//    }
//
//    @Override
//    public Iterable<Contact> listAllContacts() {
//        return contactRepository.findAll();
//    }
//
//    @Override
//    public Contact saveContact(Contact contact) {
//        return contactRepository.save(contact);
//    }
//
//    @Override
//    public Contact getContactById(Integer id) {
//        return contactRepository.findOne(id);
//    }
//
//    @Override
//    public void deleteContact(Integer id) {
//        contactRepository.delete(id);
//    }
//
//}
