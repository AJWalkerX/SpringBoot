package com.ajwalker.repository;

import com.ajwalker.entity.Ogrenci;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OgrenciRepository extends JpaRepository<Ogrenci, Long> {
}
