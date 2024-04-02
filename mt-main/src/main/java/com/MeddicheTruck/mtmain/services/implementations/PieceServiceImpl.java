package com.MeddicheTruck.mtmain.services.implementations;

import com.MeddicheTruck.mtcore.base.BaseService;
import com.MeddicheTruck.mtmain.entities.Piece;
import com.MeddicheTruck.mtmain.repositories.PieceRepository;
import com.MeddicheTruck.mtmain.services.PieceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class PieceServiceImpl extends BaseService<Piece , PieceRepository> implements PieceService {

    private PieceRepository pieceRepository;

    @Override
    public String recordName() {
        return "piece";
    }
    @Autowired
    public PieceServiceImpl(PieceRepository pieceRepository) {
        super(pieceRepository);
        this.pieceRepository = pieceRepository;
    }
}
