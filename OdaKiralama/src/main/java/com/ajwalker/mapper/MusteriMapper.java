package com.ajwalker.mapper;

import com.ajwalker.dto.request.MusteriRequestDto;
import com.ajwalker.entity.Musteri;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MusteriMapper {
    MusteriMapper INSTANCE = Mappers.getMapper(MusteriMapper.class);

     Musteri fromMusteriRequestDto(final MusteriRequestDto dto);
}
