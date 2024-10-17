package com.ajwalker.mapper;

import com.ajwalker.dto.request.OgrenciEklemeRequestDto;
import com.ajwalker.entity.Ogrenci;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OgrenciMapper {
    OgrenciMapper INSTANCE = Mappers.getMapper(OgrenciMapper.class);


    Ogrenci fromOgrenciEklemeRequestDto(final OgrenciEklemeRequestDto dto);
}
