package com.ashish.threadboost.mapper;

import com.ashish.threadboost.models.User;
import com.ashish.threadboost.dto.UserRequestDto;
import com.ashish.threadboost.dto.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapUserRequestToUser(UserRequestDto userRequestDto){
        return new User(userRequestDto.getUserName(), userRequestDto.getClassEnrolled(), userRequestDto.getMarks());
    }

    public UserResponseDto mapUserToUserResponseDto(User user){
        return new UserResponseDto(user.getId(), user.getUserName(), user.getClassEnrolled(), user.getMarks());
    }
}
