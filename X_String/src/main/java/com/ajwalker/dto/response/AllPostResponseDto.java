package com.ajwalker.dto.response;

public record AllPostResponseDto(
        Long id,
        String username,
        String name,
        String avatar,
        String comment,
        String imageUrl,
        Long date,
        Integer commentCount,
        Integer likeCount,
        Integer viewCount
) {

}
