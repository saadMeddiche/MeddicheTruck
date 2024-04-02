package com.MeddicheTruck.mtmain.services.implementations;

import com.MeddicheTruck.mtcore.base.BaseService;
import com.MeddicheTruck.mtmain.entities.PieceImage;
import com.MeddicheTruck.mtmain.repositories.PieceImageRepository;
import com.MeddicheTruck.mtmain.services.PieceImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class PieceImageServiceImpl extends BaseService<PieceImage, PieceImageRepository> implements PieceImageService {
    @Override
    protected String recordName() {
        return "piece image";
    }

    @Autowired
    public PieceImageServiceImpl(PieceImageRepository pieceImageRepository) {
        super(pieceImageRepository);
    }

}
