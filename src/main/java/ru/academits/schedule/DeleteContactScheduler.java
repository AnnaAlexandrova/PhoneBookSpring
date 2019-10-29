package ru.academits.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.academits.model.Contact;
import ru.academits.phonebook.PhoneBookController;
import ru.academits.service.ContactService;

import java.util.List;

@Component
public class DeleteContactScheduler {
    private static final Logger logger = LoggerFactory.getLogger(PhoneBookController.class);
    private final ContactService contactService;

    @Autowired
    public DeleteContactScheduler(ContactService contactService) {
        this.contactService = contactService;
    }

    @Scheduled(fixedRate = 10000)
    public void deleteRandomContact(){
        System.out.println("Scheduler!");

        List<Contact> contactList = contactService.getAllContacts();
        int contactListSize = contactList.size();
        int randomId = (int)(Math.random() * contactListSize - 1);

        if(contactListSize < 1) {
            logger.info("Can't delete random contact: contact list is empty");
        } else {
            logger.info("Removed contact with id: " + randomId);
            contactService.deleteContact(contactList.get(randomId).getId());
        }
    }
}
