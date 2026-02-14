package Tileproject.controller;

import Tileproject.model.ContactMessage;
import Tileproject.repository.ContactRepository;
import Tileproject.service.EmailService;

import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    private final ContactRepository contactRepo;
    private final EmailService emailService;

    public ContactController(ContactRepository contactRepo,
                             EmailService emailService) {
        this.contactRepo = contactRepo;
        this.emailService = emailService;
    }

    @PostMapping
    public void send(@RequestBody ContactMessage message) {

        contactRepo.save(message);          // save in DB
        emailService.sendSupportMail(message); // send email
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<ContactMessage> getAll() {
        return contactRepo.findAll();
    }
}
