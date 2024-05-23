package com.MeddicheTruck.mtmain.services.concretes;

import com.MeddicheTruck.mtmain.properties.Domain;
import com.MeddicheTruck.mtmain.properties.StaticContent;
import com.MeddicheTruck.mtmain.properties.interfaces.StorageProperties;
import com.MeddicheTruck.mtmain.services.FileSystemStorageService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class VehicleImageSystemStorageService extends FileSystemStorageService {
    public VehicleImageSystemStorageService(
            @Qualifier("vehicleImageStorageProperties") StorageProperties properties,
            StaticContent staticContent,
            Domain domain) {
        super(properties, staticContent , domain);
    }
}
