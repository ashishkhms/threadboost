package com.ashish.threadboost.controller;

import com.ashish.threadboost.service.FileService;
import com.ashish.threadboost.service.SyncUserService;
import dto.FileDto;
import dto.UserRequestDto;
import dto.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("sync")
public class SyncController {

    @Autowired
    private SyncUserService syncUserService;

    @Autowired
    private FileService fileService;

    @PostMapping("addUser")
    public UserResponseDto addUser(@RequestBody UserRequestDto userRequestDto){
        return syncUserService.addUser(userRequestDto);
    }

    @GetMapping("getUser/{userName}")
    public UserResponseDto getUser(@PathVariable String userName){
        return syncUserService.getUserByUserName(userName);
    }

    @PostMapping("writeFile")
    public String writeFile(@RequestBody FileDto fileDto) throws IOException {
        return fileService.writeToFileSync(fileDto.getContent());
    }

    @GetMapping("readFile")
    public String readFile() throws IOException{
        return fileService.readFromFileSync();
    }
}
