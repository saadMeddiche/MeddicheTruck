package com.MeddicheTruck.mtmain.services;

import com.MeddicheTruck.mtcore.base.BaseServiceInterface;
import com.MeddicheTruck.mtcore.controllers.CustomPageResponse;
import com.MeddicheTruck.mtmain.dtos.VehicleImageIDto;
import com.MeddicheTruck.mtmain.dtos.VehicleImageODto;
import com.MeddicheTruck.mtmain.entities.VehicleImage;
import org.springframework.data.domain.Pageable;

public interface VehicleImageService extends BaseServiceInterface<VehicleImage, VehicleImageIDto, VehicleImageODto> {

    CustomPageResponse<VehicleImage, VehicleImageODto> getVehicleImagesByVehicleId(Long vehicleId , String searchTerm , Pageable pageable);
}
