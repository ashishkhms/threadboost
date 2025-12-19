package com.ashish.threadboost.service;

import jakarta.annotation.PostConstruct;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;

@Service
public class FileService {
    private static String filePath = "sample.txt";

    @PostConstruct
    void init() throws IOException {
        Path path = Paths.get(filePath);
        if(Files.notExists(path)){
            Files.createFile(path);
        }
    }

    public String writeToFileSync(String content) throws IOException {
        Path path = Paths.get(filePath);
        Files.writeString(path, content, java.nio.file.StandardOpenOption.CREATE,
                java.nio.file.StandardOpenOption.TRUNCATE_EXISTING,
                java.nio.file.StandardOpenOption.DSYNC);
        return content;
    }

    public String readFromFileSync() throws IOException {
        Path path = Paths.get(filePath);
        return Files.readString(path);
    }

    @Async
    public CompletableFuture<String> writeToFileAsync(String content) throws IOException {
        Path path = Paths.get(filePath);
        Files.writeString(path, content, java.nio.file.StandardOpenOption.CREATE,
                java.nio.file.StandardOpenOption.TRUNCATE_EXISTING,
                java.nio.file.StandardOpenOption.DSYNC);
        return CompletableFuture.completedFuture(content);
    }

    @Async
    public CompletableFuture<String> readFromFileAsync() throws IOException {
        Path path = Paths.get(filePath);
        return CompletableFuture.completedFuture(Files.readString(path));
    }
}
