package com.MeddicheTruck.mtmain.services;

import com.MeddicheTruck.mtcore.base.BaseServiceInterface;
import com.MeddicheTruck.mtmain.dtos.PieceDto;
import com.MeddicheTruck.mtmain.entities.Piece;

public interface PieceService extends BaseServiceInterface<Piece , PieceDto , PieceDto> {
    Boolean isInStock(Long id);
}
