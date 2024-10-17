package com.ajwalker.controller;

import com.ajwalker.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/message")
@RequiredArgsConstructor //Constructor injection işlemini gerçekleştirir. Lombok'un sağladığı bir anatasyondur.
public class MessageController {
    private final MessageService messageService;
}
