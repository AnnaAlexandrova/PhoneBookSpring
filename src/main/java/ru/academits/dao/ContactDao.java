package ru.academits.dao;

import org.springframework.stereotype.Repository;
import ru.academits.model.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class ContactDao {
    private List<Contact> contactList = new ArrayList<>();
    private AtomicInteger idSequence = new AtomicInteger(0);

    public ContactDao() {
        Contact contact = new Contact();
        contact.setId(getNewId());
        contact.setFirstName("Иван");
        contact.setLastName("Иванов");
        contact.setPhone("9123456789");
        contactList.add(contact);
    }

    private int getNewId() {
        return idSequence.addAndGet(1);
    }

    public List<Contact> getAllContacts() {
        return contactList;
    }

    public void add(Contact contact) {
        contact.setId(getNewId());
        contactList.add(contact);
    }

    public void deleteContact(int contactId) {
        Contact contactForRemoving = contactList.stream().filter(contact -> contact.getId() == contactId).findFirst().get();
        contactList.remove(contactForRemoving);
    }

    public List<Contact> searchContact(String queryString) {
        return contactList.stream()
                .filter(contact -> contact.getFirstName().toUpperCase().contains(queryString)
                        || contact.getLastName().toUpperCase().contains(queryString)
                        || contact.getPhone().toUpperCase().contains(queryString))
                .collect(Collectors.toList());
    }
}
