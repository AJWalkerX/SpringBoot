package com.ajwalker.dto.request;

public record OdaKiralamaRequestDto(
         Long odaId,
        Long musteriId,
        Integer kiralanacakGunSayisi
) {
}
