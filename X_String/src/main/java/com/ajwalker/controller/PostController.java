package com.ajwalker.controller;

import com.ajwalker.dto.request.NewPostRequestDto;
import com.ajwalker.dto.response.AllPostResponseDto;
import com.ajwalker.dto.response.BaseResponse;
import com.ajwalker.entity.Post;
import com.ajwalker.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ajwalker.constants.RestAPIs.*;

@RestController
@RequestMapping(POST)
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;


    @PostMapping(SHARE)
    public ResponseEntity<BaseResponse<Boolean>> sharePost(@RequestBody @Valid NewPostRequestDto dto) {
        postService.sharePost(dto);
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                .data(true)
                .code(200)
                .success(true)
                .message("Yeni post oluşturuldu!")
                .build());
    }

    @GetMapping(FIND_BY_ID)
    public ResponseEntity<BaseResponse<List<Post>>> getAllMyPosts(String token) {
        return ResponseEntity.ok(BaseResponse.<List<Post>>builder()
                        .message("Tüm postlar getirildi!")
                        .success(true)
                        .code(200)
                        .data(postService.getAllMyPosts(token))
                .build());
    }

    @GetMapping(FIND_ALL)
    public ResponseEntity<BaseResponse<List<AllPostResponseDto>>> getAllPosts(String token){
        return ResponseEntity.ok(
                BaseResponse.<List<AllPostResponseDto>>builder()
                        .code(200)
                        .data(postService.getAllPosts(token))
                        .success(true)
                        .message("tüm postlar getirildi.")
                        .build()
        );
    }
}
