package com.MeddicheTruck.mtmain.services.implementations;

import com.MeddicheTruck.mtcore.base.BaseService;
import com.MeddicheTruck.mtmain.dtos.PieceDto;
import com.MeddicheTruck.mtmain.entities.Piece;
import com.MeddicheTruck.mtmain.repositories.PieceRepository;
import com.MeddicheTruck.mtmain.services.PieceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class PieceServiceImpl extends BaseService<Piece , PieceDto, PieceRepository> implements PieceService {

    @Override
    public String recordName() {
        return "piece";
    }
    @Autowired
    public PieceServiceImpl(PieceRepository pieceRepository) {
        super(pieceRepository , Piece.class , PieceDto.class);
    }
}
