package com.MeddicheTruck.mtcore.services;

import com.google.common.io.Files;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;


public class FileStorageService {

    // The resource path of the mt-main module
    private static final String DEFAULT_RESOURCE_PATH = "mt-main/src/main/resources/";

    // The folder inside the resource path where the files (images) will be stored
    private static final String DEFAULT_FILE_STORAGE_PATH = "/images/";

    // The extension of the files that will be stored
    private static final String EXTENSION = ".png";

    public static String storeFile(byte[] fileInBase64Format, String fileName , String folderName)  {

        String fullPath = getFullPath("pieces" , fileName);

        File file = new File(fullPath);

        createParentDirectories(file);

        writeToFile(fileInBase64Format, file);

        return fullPath;
    }

    // Creates all necessary parent directories for the provided File object.
    private static void createParentDirectories(File file) {
        try {
            Files.createParentDirs(file);
        } catch (IOException e) {
            throw new RuntimeException("Failed to create parent directories for file: " + file.getAbsolutePath(), e);
        }
    }


    // This method attempts to write the byte array to the file specified.
    private static void writeToFile(byte[] fileInBase64Format, File file) {
        try {
            Files.write(fileInBase64Format, file);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write file: " + file.getAbsolutePath(), e);
        }
    }


    // Returns the full path of the file to be stored
    private static String getFullPath(String folderName , String fileName) {

        try {
           URL resourceUrl = ResourceUtils.getURL(DEFAULT_RESOURCE_PATH);

           String resourcePath = Paths.get(resourceUrl.toURI()).toString();

            return Paths.get(resourcePath, DEFAULT_FILE_STORAGE_PATH, folderName, fileName + EXTENSION).toString();

        } catch (FileNotFoundException e) {
            throw new RuntimeException("[Warning] The resource path was not found , that could cause a problem with the file storage service" , e);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while getting the full path", e);
        }


    }
}
