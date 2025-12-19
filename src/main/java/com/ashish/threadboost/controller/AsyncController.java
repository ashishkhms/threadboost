package com.ashish.threadboost.controller;

import com.ashish.threadboost.service.AsyncUserService;
import com.ashish.threadboost.service.FileService;
import dto.FileDto;
import dto.UserRequestDto;
import dto.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("async")
public class AsyncController {

    @Autowired
    private AsyncUserService asyncUserService;

    @Autowired
    private FileService fileService;

    @PostMapping("addUser")
    public CompletableFuture<UserResponseDto> addUser(@RequestBody UserRequestDto userRequestDto){
        return asyncUserService.addUser(userRequestDto);
    }

    @GetMapping("getUser/{userName}")
    public CompletableFuture<UserResponseDto> getUser(@PathVariable String userName){
        return asyncUserService.getUserByUserName(userName);
    }

    @PostMapping("writeFile")
    public CompletableFuture<String> writeFile(@RequestBody FileDto fileDto) throws IOException {
        return fileService.writeToFileAsync(fileDto.getContent());
    }

    @GetMapping("readFile")
    public CompletableFuture<String> readFile() throws IOException{
        return fileService.readFromFileAsync();
    }
}
