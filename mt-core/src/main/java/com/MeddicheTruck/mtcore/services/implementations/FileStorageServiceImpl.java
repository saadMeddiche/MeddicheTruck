package com.MeddicheTruck.mtcore.services.implementations;

import com.MeddicheTruck.mtcore.services.FileStorageSystem;
import com.google.common.io.Files;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;


@Service
@Getter
@Setter
public class FileStorageServiceImpl implements FileStorageSystem {

    // The resource path of the mt-main module
    @Value("${default.resource.path}")
    private String DEFAULT_RESOURCE_PATH ;

    // The folder inside the resource path where the files (images) will be stored
    @Value("${default.file.storage.path}")
    private String DEFAULT_FILE_STORAGE_PATH;

    // The extension of the files that will be stored
    @Value("${default.extension}")
    private String DEFAULT_EXTENSION;

    public String store(byte[] fileInBase64Format, String fileName , String folderName)  {

        String fullPath = getFullPath(folderName , fileName);

        File file = new File(fullPath);

        createParentDirectories(file);

        writeToFile(fileInBase64Format, file);

        return fullPath;
    }

    // Creates all necessary parent directories for the provided File object.
    private void createParentDirectories(File file) {
        try {
            Files.createParentDirs(file);
        } catch (IOException e) {
            throw new RuntimeException("Failed to create parent directories for file: " + file.getAbsolutePath(), e);
        }
    }


    // This method attempts to write the byte array to the file specified.
    private void writeToFile(byte[] fileInBase64Format, File file) {
        try {
            Files.write(fileInBase64Format, file);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write file: " + file.getAbsolutePath(), e);
        }
    }


    // Returns the full path of the file to be stored
    private String getFullPath(String folderName , String fileName) {

        try {
           URL resourceUrl = ResourceUtils.getURL(DEFAULT_RESOURCE_PATH);

           String resourcePath = Paths.get(resourceUrl.toURI()).toString();

            return Paths.get(resourcePath, DEFAULT_FILE_STORAGE_PATH, folderName, fileName + DEFAULT_EXTENSION).toString();

        } catch (FileNotFoundException e) {
            throw new RuntimeException("[Warning] The resource path was not found , that could cause a problem with the file storage service" , e);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while getting the full path", e);
        }


    }
}
