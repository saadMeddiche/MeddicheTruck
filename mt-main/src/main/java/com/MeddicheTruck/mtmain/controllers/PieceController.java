package com.MeddicheTruck.mtmain.controllers;

import com.MeddicheTruck.mtmain.dtos.PieceDto;
import com.MeddicheTruck.mtmain.entities.Piece;
import com.MeddicheTruck.mtmain.services.PieceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pieces")
@RequiredArgsConstructor
public class PieceController {

    private final PieceService pieceService;

    private final ModelMapper modelMapper = new ModelMapper();

    @GetMapping()
    public ResponseEntity<?> dynamicSearch(
            @RequestParam(defaultValue = "") String searchTerm ,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        PageRequest pageable = PageRequest.of(page, size);

        Page<Piece> piecePage = pieceService.dynamicSearch(searchTerm, pageable);

        return ResponseEntity.ok(new CustomPageResponse<Piece , PieceDto>(piecePage , PieceDto.class));
    }

    @PostMapping
    public ResponseEntity<?> createPiece(@Valid @RequestBody PieceDto pieceDto) {

        Piece piece = modelMapper.map(pieceDto, Piece.class);

        Piece addedPiece = pieceService.save(piece);

        PieceDto addedPieceDto = modelMapper.map(addedPiece, PieceDto.class);

        return ResponseEntity.ok(addedPieceDto);
    }

    @PutMapping()
    public ResponseEntity<?> updatePiece(@Valid @RequestBody PieceDto pieceDto) {

        if(pieceService.existsById(pieceDto.getId())) ResponseEntity.notFound().build();

        Piece piece = modelMapper.map(pieceDto, Piece.class);

        Piece updatedPiece = pieceService.update( piece);

        PieceDto updatedPieceDto = modelMapper.map(updatedPiece, PieceDto.class);

        return ResponseEntity.ok(updatedPieceDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePiece(@PathVariable Long id) {
        if(pieceService.existsById(id)) ResponseEntity.notFound().build();

        pieceService.deleteById(id);

        return ResponseEntity.ok().build();
    }

}
