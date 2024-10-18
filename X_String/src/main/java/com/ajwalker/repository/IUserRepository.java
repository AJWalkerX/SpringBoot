package com.ajwalker.repository;

import com.ajwalker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {

    Optional<User> findOptionalByUsernameAndPassword(String username, String password);
}
