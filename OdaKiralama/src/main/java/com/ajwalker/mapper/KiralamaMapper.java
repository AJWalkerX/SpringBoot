package com.ajwalker.mapper;

import com.ajwalker.dto.request.OdaKiralamaRequestDto;
import com.ajwalker.entity.Kiralama;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface KiralamaMapper {
     KiralamaMapper INSTANCE = Mappers.getMapper(KiralamaMapper.class);

     Kiralama fromOdaKiralamaRequestDto(final OdaKiralamaRequestDto dto);
}
