package com.MeddicheTruck.mtmain.properties;

import com.MeddicheTruck.mtmain.properties.interfaces.StorageProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "upload.vehicle-images")
@Data
public class VehicleImageStorageProperties implements StorageProperties {
    private String directory;
    private String maxFileSize;
    private List<String> allowedExtensions;
}
