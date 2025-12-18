package com.ashish.threadboost.controller;

import com.ashish.threadboost.service.SyncUserService;
import dto.UserRequestDto;
import dto.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sync")
public class SyncController {

    @Autowired
    private SyncUserService syncUserService;

    @PostMapping("addUser")
    public UserResponseDto addUser(@RequestBody UserRequestDto userRequestDto){
        return syncUserService.addUser(userRequestDto);
    }

    @GetMapping("getUser/{userName}")
    public UserResponseDto getUser(@PathVariable String userName){
        return syncUserService.getUserByUserName(userName);
    }
}
