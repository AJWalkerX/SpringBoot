package com.ajwalker.utility;

import com.ajwalker.entity.UserRole;
import com.ajwalker.service.UserRoleService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequiredArgsConstructor
public class DefaultData {

    private final UserRoleService userRoleService;

    @PostConstruct
    public void init(){
        UserRole userRole = UserRole.builder()
                .role("ADMIN")
                .userId(1l)
                .build();
        userRoleService.save(userRole);
    }
}
