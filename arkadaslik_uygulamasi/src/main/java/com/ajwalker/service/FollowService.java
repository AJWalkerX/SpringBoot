package com.ajwalker.service;

import com.ajwalker.dto.request.AddFollowRequestDto;
import com.ajwalker.entity.Follow;
import com.ajwalker.entity.User;
import com.ajwalker.exception.ArkadaslikException;
import com.ajwalker.exception.ErrorType;
import com.ajwalker.repository.IFollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final IFollowRepository repository;
    private final UserService userService;

    public void addFollow(AddFollowRequestDto dto) {
        if (!userService.existsById(dto.getUserId()) ||
                !userService.existsById(dto.getFollowingId())) {
            throw new ArkadaslikException(ErrorType.FOLLOW_USERID_FOLLOWINGID_NOTFOUND);
        }
        repository.save(
                Follow.builder().userId(dto.getUserId())
                        .followId(dto.getFollowingId())
                        .build()
        );
    }

    public List<User> getMyFollowing(Long userId) {
        List<Long> takipcilerIdList = repository.findAllFollowIdByUserId(userId);
        return userService.findAllByIdIn(takipcilerIdList);
    }
}
