package com.ms.userservice.service;

import com.ms.userservice.dto.ResponseDto;
import com.ms.userservice.entity.User;

public interface UserService {
    User saveUser(User user);

    ResponseDto getUser(Long userId);
}