package com.MeddicheTruck.mtmain.controllers;

import com.MeddicheTruck.mtcore.base.BaseController;
import com.MeddicheTruck.mtmain.dtos.PieceDto;
import com.MeddicheTruck.mtmain.entities.Piece;
import com.MeddicheTruck.mtmain.services.PieceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pieces")
public class PieceController extends BaseController<Piece, PieceDto, PieceDto, PieceService>{

    @Autowired
    PieceController(PieceService pieceService) {
        super(pieceService);
    }


}
