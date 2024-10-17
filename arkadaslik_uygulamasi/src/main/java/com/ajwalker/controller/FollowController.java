package com.ajwalker.controller;

import static com.ajwalker.constans.RestAPIs.*;

import com.ajwalker.dto.request.AddFollowRequestDto;
import com.ajwalker.dto.response.BaseResponse;
import com.ajwalker.entity.Follow;
import com.ajwalker.entity.User;
import com.ajwalker.service.FollowService;
import com.ajwalker.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(FOLLOW)
@RequiredArgsConstructor
public class FollowController {
    private final FollowService followService;

    /*
    * HTTP Request Types
    * GET: Public veriler için kullanılır. domain isminin yanına eklenir. Get genellikle browser isteğidir.
    * Header içersine eklenir.
    * POST: Sunucu tarafında yeni bir veri yaratmak amacı ile kullanılır. Kullanıcı kaydı gibi.
    * Bilgiler isteğin gövdesinde gönderilir. Veriler güvenli bir şekilde gönderilir.
    */

    @PostMapping(ADD_FOLLOW)
    public ResponseEntity<BaseResponse<Boolean>> addFollow(@RequestBody @Valid AddFollowRequestDto dto){
        followService.addFollow(dto);
        return ResponseEntity.ok(
                BaseResponse.<Boolean>builder()
                        .data(true)
                        .success(true)
                        .code(200)
                        .message("OK")
                        .build()
        );
    }

    @GetMapping("find-followers")
    public ResponseEntity<BaseResponse<List<User>>> getMyFollowing(Long userId){
        return ResponseEntity.ok(
                BaseResponse.<List<User>>builder()
                        .data(followService.getMyFollowing(userId))
                        .success(true)
                        .code(200)
                        .message("OK")
                        .build()
        );
    }

}
