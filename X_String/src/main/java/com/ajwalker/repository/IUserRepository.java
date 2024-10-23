package com.ajwalker.repository;

import com.ajwalker.entity.User;
import com.ajwalker.view.VwUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {

    Optional<User> findOptionalByUsernameAndPassword(String username, String password);

    @Query("SELECT new com.ajwalker.view.VwUser (u.id, u.username, u.name, u.avatar) " +
            "FROM User u WHERE u.id IN(?1)")
    List<VwUser> findAllByUserIds(List<Long> ids);
}
