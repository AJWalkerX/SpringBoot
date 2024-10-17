package com.ajwalker.dto.request;

import com.ajwalker.entity.OdaType;

public record OdaEklemeRequestDto(
        String odaNo,
        String katNo,
        OdaType odaType
) {
}
