package com.MeddicheTruck.mtcore.services;

import com.google.common.io.Files;
import lombok.SneakyThrows;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.file.Paths;


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


        try {
           URL resourceUrl = ResourceUtils.getURL(DEFAULT_RESOURCE_PATH);

           String resourcePath = Paths.get(resourceUrl.toURI()).toString();

            return Paths.get(resourcePath, DEFAULT_FILE_STORAGE_PATH, folderName, photoName + EXTENSION).toString();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("[Warning] The resource path was not found , that could cause a problem with the file storage service" , e);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while getting the full path", e);
        }


    }
}
