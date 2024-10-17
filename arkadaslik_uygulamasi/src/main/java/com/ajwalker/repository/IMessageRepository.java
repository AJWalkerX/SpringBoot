package com.ajwalker.repository;

import com.ajwalker.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMessageRepository extends JpaRepository<Message, Long> {
}
