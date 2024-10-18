package com.ajwalker.controller;

import com.ajwalker.dto.request.LoginRequestDto;
import com.ajwalker.dto.request.RegisterRequestDto;
import com.ajwalker.dto.response.BaseResponse;
import com.ajwalker.entity.User;
import com.ajwalker.exception.ErrorType;
import com.ajwalker.exception.XException;
import com.ajwalker.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ajwalker.constants.RestAPIs.*;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(REGISTER)
    public ResponseEntity<BaseResponse<Boolean>> register(@RequestBody @Valid RegisterRequestDto dto) {
        if (!dto.password().equals(dto.rePassword())){
            throw new XException(ErrorType.PASSWORD_ERROR);
        }
        userService.register(dto);
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                .data(true)
                .code(200)
                .success(true)
                .message("User registered")
                .build());
    }

    @PostMapping(LOGIN)
    public  ResponseEntity<BaseResponse<String>>login(@RequestBody @Valid LoginRequestDto dto){
        String token = userService.login(dto);
        return ResponseEntity.ok(BaseResponse.<String>builder()
                        .message("Login successful")
                        .success(true)
                        .code(200)
                        .data(token)
                .build());
    }

    @GetMapping(GET_PROFILE)
    public  ResponseEntity<BaseResponse<User>>getProfile(String token){
        return ResponseEntity.ok(BaseResponse.<User>builder()
                .message("Profile information")
                .success(true)
                .code(200)
                .data(userService.getProfile(token))
                .build());
    }
}
