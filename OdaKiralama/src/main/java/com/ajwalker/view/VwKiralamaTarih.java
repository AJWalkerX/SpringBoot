package com.ajwalker.view;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class VwKiralamaTarih {
    long odaId;
    Long kiralananGun;
    Integer kiralanacakGunSayisi;
}
