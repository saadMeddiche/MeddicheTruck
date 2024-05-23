package com.MeddicheTruck.mtmain.services;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    String uploadFile(MultipartFile file);

    void deleteFile(String fileName);

    void init();
}
