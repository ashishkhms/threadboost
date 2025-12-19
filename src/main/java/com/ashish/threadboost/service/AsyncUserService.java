package com.ashish.threadboost.service;
import com.ashish.threadboost.mapper.UserMapper;
import com.ashish.threadboost.models.User;
import com.ashish.threadboost.repository.UserRepository;
import com.ashish.threadboost.dto.UserRequestDto;
import com.ashish.threadboost.dto.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class AsyncUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Async
    public CompletableFuture<UserResponseDto> addUser(UserRequestDto userRequestDto) {
        User user = userMapper.mapUserRequestToUser(userRequestDto);
        userRepository.save(user);
        return CompletableFuture.completedFuture(userMapper.mapUserToUserResponseDto(user));
    }

    @Async
    public CompletableFuture<UserResponseDto> getUserByUserName(String userName){
        User user = userRepository.findFirstByUserName(userName);
        return CompletableFuture.completedFuture(userMapper.mapUserToUserResponseDto(user));
    }
}
