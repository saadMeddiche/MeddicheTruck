package com.MeddicheTruck.mtmain.services.implementations;

import com.MeddicheTruck.mtcore.base.BaseService;
import com.MeddicheTruck.mtcore.controllers.CustomPageResponse;
import com.MeddicheTruck.mtcore.handlingExceptions.costumExceptions.DoNotExistException;
import com.MeddicheTruck.mtcore.services.FileStorageSystem;
import com.MeddicheTruck.mtcore.services.Naming;
import com.MeddicheTruck.mtmain.dtos.PieceImageIDto;
import com.MeddicheTruck.mtmain.dtos.PieceImageODto;
import com.MeddicheTruck.mtmain.entities.PieceImage;
import com.MeddicheTruck.mtmain.repositories.PieceImageRepository;
import com.MeddicheTruck.mtmain.services.PieceImageService;
import com.MeddicheTruck.mtmain.services.PieceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Primary
public class PieceImageServiceImpl extends BaseService<PieceImage, PieceImageIDto, PieceImageODto, PieceImageRepository> implements PieceImageService {

    FileStorageSystem fileStorageSystem;

    Naming naming;

    PieceService pieceService;

    @Override
    protected String recordName() {
        return "piece image";
    }

    @Autowired
    public PieceImageServiceImpl(PieceImageRepository pieceImageRepository , FileStorageSystem fileStorageSystem , Naming naming , PieceService pieceService) {
        super(pieceImageRepository, PieceImage.class, PieceImageIDto.class , PieceImageODto.class);
        this.fileStorageSystem = fileStorageSystem;
        this.naming = naming;
        this.pieceService = pieceService;
    }

    @Override
    public void beforeSave(PieceImage pieceImage , PieceImageIDto pieceImageIDto) {

        // Check if the piece exists in the database before saving the piece image
        if(!pieceService.existsById(pieceImageIDto.getPieceId()))
            throw new DoNotExistException(String.format("The piece with id %d does not exist", pieceImageIDto.getPieceId()));

        // Store the piece image , and set the photo path of the piece image to the path of the stored image
        pieceImage.setPhotoPath(
                fileStorageSystem.store(
                        pieceImageIDto.getPhotoInBase64(),
                         naming.uniquifyWord(pieceImageIDto.getName()),
                        "pieces")
        );

    }

    @Override
    public CustomPageResponse<PieceImage , PieceImageODto> getPieceImagesByPieceId(Long pieceId , String searchTerm, Pageable pageable) {
        Page<PieceImage> pieceImagePage =  repository.findPieceImagesByPieceId(pieceId ,searchTerm, pageable);
        return new CustomPageResponse<>(pieceImagePage , PieceImageODto.class);
    }

}
