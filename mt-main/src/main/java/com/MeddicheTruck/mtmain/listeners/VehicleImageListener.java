package com.MeddicheTruck.mtmain.listeners;

import com.MeddicheTruck.mtmain.entities.VehicleImage;
import com.MeddicheTruck.mtmain.services.concretes.VehicleImageSystemStorageService;
import jakarta.persistence.PreRemove;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VehicleImageListener {

    private final VehicleImageSystemStorageService vehicleImageSystemStorageService;

    @PreRemove
    public void afterAnyDelete(VehicleImage vehicleImage) {
        String photoName = vehicleImageSystemStorageService
                .extractFileNameFromPhotoUrl(vehicleImage.getPhotoPath());

        vehicleImageSystemStorageService.deleteFile(photoName);
    }

}
