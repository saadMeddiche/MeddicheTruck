package com.MeddicheTruck.mtmain.controllers;

import com.MeddicheTruck.mtcore.base.BaseController;
import com.MeddicheTruck.mtmain.dtos.PieceImageIDto;
import com.MeddicheTruck.mtmain.dtos.PieceImageODto;
import com.MeddicheTruck.mtmain.entities.PieceImage;
import com.MeddicheTruck.mtmain.services.PieceImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pieceImages")
public class PieceImageController extends BaseController<PieceImage, PieceImageIDto, PieceImageODto, PieceImageService > {

    @Autowired
    PieceImageController(PieceImageService pieceImageService) {
        super(pieceImageService);
    }

    @GetMapping("/piece/{pieceId}")
    public ResponseEntity<?> getPieceImagesByPieceId(@PathVariable Long pieceId ,
                                                     @RequestParam(defaultValue = "") String searchTerm ,
                                                     @RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "5") int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(service.getPieceImagesByPieceId(pieceId , searchTerm, pageable));
    }

}
