package com.ajwalker.repository;

import com.ajwalker.entity.Kiralama;
import com.ajwalker.view.VwKiralamaTarih;
import jakarta.websocket.server.PathParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KiralamaRepository extends JpaRepository<Kiralama, Long> {

    @Query("SELECT k.odaId FROM Kiralama  k WHERE k.bitisTarihi < :bugun")
    List<Long> findByBitisTarihi(@PathParam("bugun") Long bugun);
}
