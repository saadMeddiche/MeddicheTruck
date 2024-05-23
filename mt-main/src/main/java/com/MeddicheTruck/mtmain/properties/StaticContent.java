package com.MeddicheTruck.mtmain.properties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.nio.file.Path;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "static-upload")
public class StaticContent {

    private Path uploadDirectory;

    @Getter @Setter
    private String directory;

    @Getter @Setter
    private String url;

    public void setUploadDirectory(String uploadDirectory) {
        this.uploadDirectory = Path.of(uploadDirectory);
        this.directory = uploadDirectory;
    }

    public Path getUploadDirectory() {
        return uploadDirectory;
    }

    public String getUploadDirectoryAbsolutePath() {
        return String.format("%s%s", uploadDirectory.toAbsolutePath(), File.separator);
    }
}
