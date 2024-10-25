package com.ajwalker.mailsenderdemo;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class MailSenderDemoApplication {
    @Autowired
    private EmailSenderService senderService;

    public static void main(String[] args) {
        SpringApplication.run(MailSenderDemoApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void sendMail() {
        senderService.sendSimpleMail("alexanderjoseph.walker@bilgeadamboost.com","Deneme","Deneme mail'idir");
    }
}
