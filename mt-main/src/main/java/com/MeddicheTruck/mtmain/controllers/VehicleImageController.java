package com.MeddicheTruck.mtmain.controllers;

import com.MeddicheTruck.mtcore.base.BaseController;
import com.MeddicheTruck.mtmain.dtos.VehicleImageIDto;
import com.MeddicheTruck.mtmain.dtos.VehicleImageODto;
import com.MeddicheTruck.mtmain.entities.VehicleImage;
import com.MeddicheTruck.mtmain.services.VehicleImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vehicleImages")
public class VehicleImageController extends BaseController<VehicleImage, VehicleImageIDto, VehicleImageODto, VehicleImageService> {

    @Autowired
    VehicleImageController(VehicleImageService vehicleImageService) {
        super(vehicleImageService);
    }

    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<?> getVehicleImagesByVehicleId(@PathVariable Long vehicleId ,
                                                         @RequestParam(defaultValue = "") String searchTerm ,
                                                         @RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "5") int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(service.getVehicleImagesByVehicleId(vehicleId , searchTerm, pageable));
    }

}
