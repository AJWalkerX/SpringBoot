package com.ajwalker.service;

import com.ajwalker.dto.request.LoginRequestDto;
import com.ajwalker.dto.request.RegisterRequestDto;
import com.ajwalker.entity.User;
import com.ajwalker.entity.enums.EProfileState;
import com.ajwalker.exception.ErrorType;
import com.ajwalker.exception.XException;
import com.ajwalker.mapper.IUserMapper;
import com.ajwalker.repository.IUserRepository;
import com.ajwalker.utility.JwtManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final IUserRepository userRepository;
    private final JwtManager jwtManager;

    public void register(RegisterRequestDto dto) {
        userRepository.save(IUserMapper.INSTANCE.fromRegisterRequestDto(dto));
    }

    public String login(LoginRequestDto dto) {
        //repo'ya bu kullanıcı adı ve şifre ile bir user var mı diye soruyoruz.
        Optional<User> userOptional = userRepository.findOptionalByUsernameAndPassword(dto.username(), dto.password());
        if(userOptional.isEmpty())
            throw new XException(ErrorType.INVALID_USERNAME_OR_PASSWORD_ERROR);
        return jwtManager.createToken(userOptional.get().getId());
    }

    public User getProfile(String token) {
        Optional<Long> optionalUserId = jwtManager.validateToken(token);
        if(optionalUserId.isEmpty()) //1. aşama token kontrolü
            throw new XException(ErrorType.INVALID_TOKEN_ERROR);
        Optional<User> userOptional = userRepository.findById(optionalUserId.get());
        if (userOptional.isEmpty()) //2. aşama kullanıcı kontrollü (kullanıcı var mı?)
            throw new XException(ErrorType.USER_NOT_FOUND_ERROR);
        return userOptional.get();
    }
}
