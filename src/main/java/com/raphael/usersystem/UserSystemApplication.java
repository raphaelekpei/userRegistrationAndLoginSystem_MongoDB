package com.raphael.usersystem;

import com.raphael.usersystem.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class UserSystemApplication {

    @Autowired
    private EmailSenderService emailSenderService;

    public static void main(String[] args) {
        SpringApplication.run(UserSystemApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void triggerMail() {
        emailSenderService.sendSimpleEmail(
                "ekpeiraphael020@gmail.com",
                "This is the Email Body.....",
                "This is the Email Subject"
        );
    }

}