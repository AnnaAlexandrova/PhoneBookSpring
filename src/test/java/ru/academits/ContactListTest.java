package ru.academits;

import org.junit.Assert;
import org.junit.Test;
import ru.academits.dao.ContactDao;
import ru.academits.model.Contact;

import java.util.List;

public class ContactListTest {

    @Test
    public void getAllContactsTest() {
        ContactDao contactDao = new ContactDao();
        List<Contact> contactList = contactDao.getAllContacts();
        Assert.assertTrue(contactList.size() != 0);
    }

    @Test
    public void addContactTest() {
        Contact testContact = new Contact();
        testContact.setId(11);
        testContact.setFirstName("firstName");
        testContact.setLastName("lastName");
        testContact.setPhone("11111");
        ContactDao contactDao = new ContactDao();
        contactDao.add(testContact);
        List<Contact> contactList = contactDao.searchContact(testContact.getPhone());

        Assert.assertTrue(contactList.size() >= 1);
    }

    @Test
    public void deleteContactTest() {
        ContactDao contactDao = new ContactDao();
        List<Contact> contactList = contactDao.getAllContacts();
        contactDao.deleteContact(contactList.get(0).getId());

        Assert.assertEquals(0, contactList.size());
    }

    @Test
    public void searchContactTest() {
        ContactDao contactDao = new ContactDao();
        List<Contact> contactList = contactDao.getAllContacts();
        List<Contact> filteredList = contactDao.searchContact("Test");

        Assert.assertTrue(contactList.size() > filteredList.size());
    }
}
