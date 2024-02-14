package com.MeddicheTruck.mtcore.services;

import com.google.common.io.Files;
import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;

public class FileStorageService {

    private static final String DEFAULT_FILE_STORAGE_PATH = "/images/pieces/";

    private static final String EXTENSION = ".png";

    @SneakyThrows
    public static String storeFile(byte[] photoInBase64Format, String photoName)  {

        String fullPath = ResourceUtils.getURL("mt-main/src/main/resources/") + "images/pieces/" + photoName + EXTENSION;

        fullPath = fullPath.replace("file:/", "");

        File image = new File(fullPath);

        Files.createParentDirs(image);

        Files.write(photoInBase64Format, image);

        return fullPath;
    }

    public static void deleteFile(String photoPath) {
    }
}
