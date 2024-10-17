package com.ajwalker.mapper;

import com.ajwalker.dto.request.OdaEklemeRequestDto;
import com.ajwalker.entity.Oda;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OdaMapper {
    OdaMapper INSTANCE = Mappers.getMapper(OdaMapper.class);

    Oda fromOdaEklemeRequestDto(final OdaEklemeRequestDto dto);
}
