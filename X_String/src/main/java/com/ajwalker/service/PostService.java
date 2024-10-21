package com.ajwalker.service;

import com.ajwalker.dto.request.NewPostRequestDto;
import com.ajwalker.dto.response.AllPostResponseDto;
import com.ajwalker.entity.Post;
import com.ajwalker.exception.ErrorType;
import com.ajwalker.exception.XException;
import com.ajwalker.mapper.PostMapper;
import com.ajwalker.repository.IPostRepository;
import com.ajwalker.utility.JwtManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final IPostRepository postRepository;
    private final JwtManager jwtManager;

    public void sharePost(NewPostRequestDto dto) {
        Optional<Long> userId = jwtManager.validateToken(dto.token());
        if (userId.isEmpty()) throw new XException(ErrorType.INVALID_TOKEN_ERROR);

//        Post post = Post.builder()
//                .userId(userId.get())
//                .comment(dto.comment())
//                .date(System.currentTimeMillis())
//                .imageUrl(dto.imageUrl())
//                .state(EPostState.ACTIVE)
//                .commentCount(0)
//                .likeCount(0)
//                .viewCount(0)
//                .build();
        Post post = PostMapper.INSTANCE.fromNewPostRequestDto(dto, userId.get());
        postRepository.save(post);
    }

    public List<Post> getAllMyPosts(String token) {
        Optional<Long> userId = jwtManager.validateToken(token);
        if (userId.isEmpty()) throw new XException(ErrorType.INVALID_TOKEN_ERROR);
        return postRepository.findAllByUserId(userId.get());
    }

    public List<AllPostResponseDto> getAllPosts(String token) {
        Optional<Long> userId = jwtManager.validateToken(token);
        if (userId.isEmpty()) throw new XException(ErrorType.INVALID_TOKEN_ERROR);
        List<Post> postList = postRepository.findAll();
        /**
         * postları sıkıtlayın. mesela date e göre son altılmış 10 post
         * post listesinin içerisinden userid lerin listesini çıkartın. List<Long> userids
         * kullanıcıların listesini Map<Long, User> userList
         */
        List<AllPostResponseDto> result = new ArrayList<>();
        postList.forEach(p -> {
            p.getUserId();
        });
        return result;
    }
}