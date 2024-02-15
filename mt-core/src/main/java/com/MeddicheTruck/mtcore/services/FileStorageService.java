package com.MeddicheTruck.mtcore.services;

import com.google.common.io.Files;
import lombok.SneakyThrows;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;


public class FileStorageService {


    private static final String DEFAULT_RESOURCE_PATH = "mt-main/src/main/resources/";
    private static final String DEFAULT_FILE_STORAGE_PATH = "/images/";
    private static final String EXTENSION = ".png";

    @SneakyThrows
    public static String storeFile(byte[] photoInBase64Format, String photoName , String folderName)  {

        String fullPath = getFullPath("pieces" , photoName);

        File image = new File(fullPath);

        Files.createParentDirs(image);

        Files.write(photoInBase64Format, image);

        return fullPath;
    }

    public static void deleteFile(String photoPath) {
    }

    private static String getFullPath(String folderName , String photoName) {

        StringBuilder fullPath = new StringBuilder();

        try {
            fullPath.append(ResourceUtils.getURL(DEFAULT_RESOURCE_PATH));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("[Warning] The resource path was not found , that could cause a problem with the file storage service");
        }

        fullPath.append(DEFAULT_FILE_STORAGE_PATH);

        fullPath.append("/");

        fullPath.append(folderName);

        fullPath.append("/");

        fullPath.append(photoName);

        fullPath.append(EXTENSION);

        // Remove the file:/ from the path because ResourceUtils.getURL() returns a path with file:/ at the beginning
        return fullPath.toString().replace("file:/", "");

    }
}
