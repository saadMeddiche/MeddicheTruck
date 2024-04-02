package com.MeddicheTruck.mtmain.controllers;

import com.MeddicheTruck.mtcore.handlingExceptions.costumExceptions.ValidationException;
import com.MeddicheTruck.mtcore.services.FileStorageSystem;
import com.MeddicheTruck.mtcore.services.Naming;
import com.MeddicheTruck.mtmain.entities.Vehicle;
import com.MeddicheTruck.mtmain.entities.VehicleImage;
import com.MeddicheTruck.mtmain.repositories.VehicleImageRepository;
import com.MeddicheTruck.mtmain.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVehicleImage(@PathVariable Long id) {

        System.out.println("I am here 2");
        Optional<VehicleImage> vehicleImage = vehicleImageRepository.findById(id);

        vehicleImage.ifPresentOrElse(
                (value) ->{

                },
                () ->{
                    throw new ValidationException("Vehicle Image Does Not Exist");
                }
        );
        vehicleImageRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
