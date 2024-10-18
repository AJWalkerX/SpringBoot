package com.ajwalker.mapper;

import com.ajwalker.dto.request.RegisterRequestDto;
import com.ajwalker.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.Date;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {
    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    @Mappings({
            @Mapping(target = "memberDate", expression = "java(System.currentTimeMillis())"),
            @Mapping(target = "createDate", expression = "java(System.currentTimeMillis())"),
            @Mapping(target = "updateDate", expression = "java(System.currentTimeMillis())"),
    })
    User fromRegisterRequestDto(RegisterRequestDto dto);
}
