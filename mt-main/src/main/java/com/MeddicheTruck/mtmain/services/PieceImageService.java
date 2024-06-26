package com.MeddicheTruck.mtmain.services;

import com.MeddicheTruck.mtcore.base.BaseServiceInterface;
import com.MeddicheTruck.mtcore.controllers.CustomPageResponse;
import com.MeddicheTruck.mtmain.dtos.PieceImageIDto;
import com.MeddicheTruck.mtmain.dtos.PieceImageODto;
import com.MeddicheTruck.mtmain.entities.PieceImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PieceImageService extends BaseServiceInterface<PieceImage , PieceImageIDto, PieceImageODto> {

    CustomPageResponse<PieceImage, PieceImageODto> getPieceImagesByPieceId(Long pieceId , String searchTerm , Pageable pageable);
}
