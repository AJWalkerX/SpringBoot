package com.ajwalker.service;

import com.ajwalker.repository.IMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final IMessageRepository repository;
}
