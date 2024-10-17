package com.ajwalker.mapper;

import com.ajwalker.dto.request.UpdateUserProfileRequestDto;
import com.ajwalker.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/*
* Herhangi bir DTO'dan User entity'sine dönüştürmek işlemi işin kullanılacak
*/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    // Bu interface üzerinden oluşturulacak olan IMPL sınıfının nesnesini yaratarak
    // referans adresini atamak için kullanıyoruz.
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User fromUpdateProfileDto(final UpdateUserProfileRequestDto dto);
}
