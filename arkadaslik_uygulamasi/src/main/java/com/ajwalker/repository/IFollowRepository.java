package com.ajwalker.repository;

import com.ajwalker.entity.Follow;
import com.ajwalker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IFollowRepository extends JpaRepository<Follow, Long> {


    @Query("SELECT f.followId FROM Follow f WHERE f.userId = :userId")
    List<Long> findAllFollowIdByUserId(Long userId);
}
