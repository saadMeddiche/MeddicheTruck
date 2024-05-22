package com.MeddicheTruck.mtmain.services.implementations;

import com.MeddicheTruck.mtcore.handlingExceptions.costumExceptions.StorageException;
import com.MeddicheTruck.mtmain.properties.StorageProperties;
import com.MeddicheTruck.mtmain.services.StorageService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
public class FileSystemStorageService implements StorageService {

    private final Path rootLocation;

    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Path.of(properties.getDirectory());
    }

    @Override
    public String uploadFile(MultipartFile file) {
        try {

            if(file.isEmpty()) throw new StorageException("Failed to store empty file.");

            Path destinationFile = this.rootLocation
                    .resolve(Path.of(file.getOriginalFilename()))
                    .normalize()
                    .toAbsolutePath();

            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                throw new StorageException("Cannot store file outside current directory.");
            }

            try (InputStream inputStream = file.getInputStream()){
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
                return destinationFile.toString();
            }


        } catch (Exception e) {
            throw new StorageException("Failed to store file: " + e);
        }
    }

    @Override
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

    @Override
    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(rootLocation);
            System.out.println("Storage initialized at: " + rootLocation);
        } catch (Exception e) {
            throw new StorageException("Could not initialize storage." + e);
        }
    }
}
