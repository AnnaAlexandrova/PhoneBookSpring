package ru.academits.phonebook;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.academits.model.Contact;
import ru.academits.model.ContactValidation;
import ru.academits.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Controller
@RequestMapping("/phoneBook/rpc/api/v1")
public class PhoneBookController {
    private static final Logger logger = LoggerFactory.getLogger(PhoneBookController.class);

    private final ContactService contactService;
    private Contact contact;

    public PhoneBookController(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping(value = "getAllContacts", method = RequestMethod.GET)
    @ResponseBody
    public List<Contact> getAllContacts() {
        logger.info("called method getAllContacts");
        return contactService.getAllContacts();
    }

    @RequestMapping(value = "addContact", method = RequestMethod.POST)
    @ResponseBody
    public ContactValidation addContact(@RequestBody Contact contact) {
        logger.info("Contact was added: " + contactToString(contact));
        return contactService.addContact(contact);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public List<Contact> deleteContact(@RequestBody Contact contact) {
        logger.info("Contact was deleted: " + contactToString(contact));
        contactService.deleteContact(contact.getId());
        return contactService.getAllContacts();
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    @ResponseBody
    public List<Contact> searchContact(@RequestParam (required = false, name = "query") String query) {
        logger.info("Contacts was filtered by: " + query);
        return contactService.searchContacts(query);
    }

    private static String contactToString(Contact contact) {
        return contact.getId() + "; " + contact.getFirstName() + "; " + contact.getLastName() + "; " + contact.getPhone();
    }
}


