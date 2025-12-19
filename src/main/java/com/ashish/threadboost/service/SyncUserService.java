package com.ashish.threadboost.service;

import com.ashish.threadboost.mapper.UserMapper;
import com.ashish.threadboost.models.User;
import com.ashish.threadboost.repository.UserRepository;
import com.ashish.threadboost.dto.UserRequestDto;
import com.ashish.threadboost.dto.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SyncUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserResponseDto addUser(UserRequestDto userRequestDto) {
        User user = userMapper.mapUserRequestToUser(userRequestDto);
        userRepository.save(user);
        return userMapper.mapUserToUserResponseDto(user);
    }

    public UserResponseDto getUserByUserName(String userName){
        User user = userRepository.findFirstByUserName(userName);
        return userMapper.mapUserToUserResponseDto(user);
    }


}
