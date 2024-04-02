package com.MeddicheTruck.mtmain.controllers;

import com.MeddicheTruck.mtcore.services.FileStorageSystem;
import com.MeddicheTruck.mtcore.services.Naming;
import com.MeddicheTruck.mtmain.entities.Vehicle;
import com.MeddicheTruck.mtmain.entities.VehicleImage;
import com.MeddicheTruck.mtmain.repositories.VehicleImageRepository;
import com.MeddicheTruck.mtmain.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/vehicleImages")
public class VehicleImageController {

    VehicleImageRepository vehicleImageRepository;

    VehicleRepository vehicleRepository;
    private final FileStorageSystem fss;
    private final Naming n;

    @Autowired
    VehicleImageController(FileStorageSystem fss , Naming n , VehicleImageRepository vehicleImageRepository ,VehicleRepository vehicleRepository) {
        this.fss = fss;
        this.n = n;
        this.vehicleImageRepository = vehicleImageRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @PostMapping
    public ResponseEntity<?> createVehicleImage(@RequestBody VehicleImage vehicleImage) {

        System.out.println("Ia m here");

        Vehicle vehicle = vehicleRepository.findById(vehicleImage.getVehicle().getId()).get();

        vehicleImage.setTenant(vehicle.getTenant());
        vehicleImage.setVehicle(vehicle);

        vehicleImage.setPhotoPath(fss.store(
                vehicleImage.getPhotoInBase64Format(),
                n.uniquifyWord(vehicleImage.getName()) ,
                "vehicles")
        );

        VehicleImage savedVehicleImage = vehicleImageRepository.save(vehicleImage);
        return ResponseEntity.ok(savedVehicleImage);
    }
}
