package com.MeddicheTruck.mtmain.listeners;

import com.MeddicheTruck.mtcore.services.FileStorageSystem;
import com.MeddicheTruck.mtcore.services.Naming;
import com.MeddicheTruck.mtmain.entities.Vehicle;
import jakarta.persistence.PrePersist;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component @NoArgsConstructor
public class VehicleListener {

    private FileStorageSystem fss;
    private Naming n;

    @Autowired
    VehicleListener(FileStorageSystem fss , Naming n) {
        this.fss = fss;
        this.n = n;
    }
    @PrePersist
    public void prePersist(Vehicle vehicle) {

        Optional.ofNullable(vehicle.getImages())
                .ifPresent(images -> images.forEach(
                        image -> {
                            image.setVehicle(vehicle);
                            image.setPhotoPath(fss.store(
                                    image.getPhotoInBase64Format(),
                                    n.uniquifyWord(image.getName()) ,
                                    "vehicles")
                            );
                        }
                ));

    }
}
