package com.MeddicheTruck.mtmain.services;

import com.MeddicheTruck.mtcore.handlingExceptions.costumExceptions.StorageException;
import com.MeddicheTruck.mtmain.properties.Domain;
import com.MeddicheTruck.mtmain.properties.StaticContent;
import com.MeddicheTruck.mtmain.properties.interfaces.StorageProperties;
import jakarta.annotation.PostConstruct;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public abstract class FileSystemStorageService {

    private final Path rootLocation;

    private final StaticContent staticContent;

    private final Domain domain;

    private final StorageProperties properties;

    protected FileSystemStorageService(StorageProperties properties , StaticContent staticContent , Domain domain) {
        this.rootLocation = Path.of(String.format(
                "%s%s%s",
                staticContent.getDirectory(),
                File.separator,
                properties.getDirectory()
        ));
        this.properties = properties;
        this.staticContent = staticContent;
        this.domain = domain;
    }

    public String uploadFile(MultipartFile file) {
        try {

            if(file.isEmpty()) throw new StorageException("Failed to store empty file.");

            String fileName = String.format("%s_%s",
                    UUID.randomUUID(),
                    file.getOriginalFilename()
            );

            Path destinationFile = this.rootLocation
                    .resolve(Path.of(fileName))
                    .normalize()
                    .toAbsolutePath();

            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                throw new StorageException("Cannot store file outside current directory.");
            }

            try (InputStream inputStream = file.getInputStream()){
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
                return String.format("%s/%s/%s/%s",
                        domain.getUrl(),
                        staticContent.getUrl(),
                        properties.getDirectory(),
                        fileName
                );
            }


        } catch (Exception e) {
            throw new StorageException("Failed to store file: " + e);
        }
    }

    public void deleteFile(String fileName) {
        try {

            Path destinationFile = this.rootLocation
                    .resolve(Path.of(fileName))
                    .normalize()
                    .toAbsolutePath();

            Files.delete(destinationFile);

        } catch (Exception e) {
            throw new StorageException("Failed to delete file." + e);
        }
    }

    @PostConstruct
    private void init() {
        try {
            Files.createDirectories(staticContent.getUploadDirectory());
            System.out.println("Static upload at: " + staticContent.getUploadDirectory());

            Files.createDirectories(rootLocation);
            System.out.println("Storage initialized at: " + rootLocation);

        } catch (Exception e) {
            throw new StorageException("Could not initialize storage." + e);
        }
    }
}
