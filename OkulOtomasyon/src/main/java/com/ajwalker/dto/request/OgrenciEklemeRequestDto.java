package com.ajwalker.dto.request;


import com.ajwalker.entity.Cinsiyet;

public record OgrenciEklemeRequestDto(
        String ad,
        String soyad,
        String veli,
        String veliTelefon,
        String adres,
        Cinsiyet cinsiyet,
        Integer dogumTarihi
) {

}
