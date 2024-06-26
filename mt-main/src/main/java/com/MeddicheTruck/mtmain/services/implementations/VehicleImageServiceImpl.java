package com.MeddicheTruck.mtmain.services.implementations;

import com.MeddicheTruck.mtcore.base.BaseService;
import com.MeddicheTruck.mtcore.controllers.CustomPageResponse;
import com.MeddicheTruck.mtcore.handlingExceptions.costumExceptions.DoNotExistException;
import com.MeddicheTruck.mtmain.clones.VehicleImageUpdateClone;
import com.MeddicheTruck.mtmain.dtos.VehicleImageIDto;
import com.MeddicheTruck.mtmain.dtos.VehicleImageODto;
import com.MeddicheTruck.mtmain.entities.VehicleImage;
import com.MeddicheTruck.mtmain.repositories.VehicleImageRepository;
import com.MeddicheTruck.mtmain.services.VehicleImageService;
import com.MeddicheTruck.mtmain.services.VehicleService;
import com.MeddicheTruck.mtmain.services.concretes.VehicleImageSystemStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Primary
public class VehicleImageServiceImpl extends BaseService<VehicleImage, VehicleImageIDto, VehicleImageODto, VehicleImageRepository> implements VehicleImageService{

    VehicleService vehicleService;

    VehicleImageSystemStorageService vehicleImageSystemStorageService;


    @Override
    public String recordName() {
        return "vehicleImage";
    }

    @Autowired
    public VehicleImageServiceImpl(VehicleImageRepository vehicleImageRepository,
                                   VehicleService vehicleService,
                                   VehicleImageSystemStorageService vehicleImageSystemStorageService
    ) {

        super(vehicleImageRepository, VehicleImage.class, VehicleImageIDto.class, VehicleImageODto.class);

        this.vehicleService = vehicleService;
        this.vehicleImageSystemStorageService = vehicleImageSystemStorageService;
    }

    @Override
    public void saveValidation(VehicleImageIDto vehicleImageIDto) {
        validateObject(vehicleImageIDto);
        throwExceptionIf(vehicleService::doesNotExistById, vehicleImageIDto.getVehicleId(), DoNotExistException::new, String.format("The vehicle with id %d does not exist", vehicleImageIDto.getVehicleId()));
    }

    @Override
    public void beforeSave(VehicleImage vehicleImage, VehicleImageIDto vehicleImageIDto) {
        // Store the vehicle image , and set the photo path of the vehicle image to the path of the stored image
        String photoPath = vehicleImageSystemStorageService.uploadFile(vehicleImageIDto.getPhoto());
        vehicleImage.setPhotoPath(photoPath);
    }

    @Override
    public void updateValidation(VehicleImageIDto vehicleImageODto) {
        validateObjectAgainstAnotherObject(vehicleImageODto, VehicleImageUpdateClone.class);
    }

    @Override
    public CustomPageResponse<VehicleImage, VehicleImageODto> getVehicleImagesByVehicleId(Long vehicleId, String searchTerm, Pageable pageable) {

        throwExceptionIf(vehicleService::doesNotExistById, vehicleId, DoNotExistException::new, String.format("The vehicle with id %d does not exist", vehicleId));

        Page<VehicleImage> vehicleImages = repository.findVehicleImageByVehicleId(vehicleId, searchTerm, pageable);

        return new CustomPageResponse<>(vehicleImages, VehicleImageODto.class);
    }

}
