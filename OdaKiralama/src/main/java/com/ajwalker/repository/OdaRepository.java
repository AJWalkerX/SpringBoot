package com.ajwalker.repository;

import com.ajwalker.entity.Oda;
import com.ajwalker.entity.OdaDurum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OdaRepository extends JpaRepository<Oda, Long> {

    @Query("SELECT o FROM Oda o where o.odaDurum = :odaDurum AND o.id IN(:id)")
    public List<Oda> findAllOdaIdByStatus(OdaDurum odaDurum, List<Long> id);
}
