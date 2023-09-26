package com.itca.cursify.service.Storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageService {
    void init() throws IOException;
    String store(MultipartFile file);//Method to save file

    Resource loadAsResource(String filename);//Method to load file
}
