package com.ajwalker.repository;

import com.ajwalker.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


//@Repository spring3.x'den sonra bu anatosyonu kullanmak zorunlu değildir.
public interface IPostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByUserId(Long aLong);
}
