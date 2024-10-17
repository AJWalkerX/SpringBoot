package com.ajwalker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tbl_ogrenci")
public class Ogrenci {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long sinifId;
    private String adi;
    private String soyadi;
    private String veli;
    private String veliTelefon;
    private String adres;
    private Integer dogumTarihi;
    private Cinsiyet cinsiyet;

    private Boolean isActive;
    private Long createAt;
    private Long updateAt;
}
