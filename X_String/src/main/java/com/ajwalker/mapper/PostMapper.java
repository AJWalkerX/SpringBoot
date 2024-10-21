package com.ajwalker.mapper;


import com.ajwalker.dto.request.NewPostRequestDto;
import com.ajwalker.dto.response.AllPostResponseDto;
import com.ajwalker.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(target = "date", expression = "java(System.currentTimeMillis())")
    @Mapping(target = "commentCount", expression = "java(Integer.valueOf(0))")
    @Mapping(target = "likeCount", expression = "java(Integer.valueOf(0))")
    @Mapping(target = "viewCount", expression = "java(Integer.valueOf(0))")
    @Mapping(target = "state", expression = "java(com.ajwalker.entity.enums.EPostState.ACTIVE)")
    Post fromNewPostRequestDto(final NewPostRequestDto dto, final Long userId);

    AllPostResponseDto fromPostAndUser(final Post post, String userName, String name, String avatar);
}
