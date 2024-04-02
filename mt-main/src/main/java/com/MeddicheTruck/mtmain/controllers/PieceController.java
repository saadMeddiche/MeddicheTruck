package com.MeddicheTruck.mtmain.controllers;

import com.MeddicheTruck.mtcore.controllers.CustomPageResponse;
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

        return ResponseEntity.ok(pieceService.dynamicSearch(searchTerm, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPiece(@PathVariable Long id) {
        if(!pieceService.existsById(id)) return ResponseEntity.notFound().build();

        PieceDto pieceDto = pieceService.findById(id);

        return ResponseEntity.ok(pieceDto);
    }

    @PostMapping
    public ResponseEntity<?> createPiece(@Valid @RequestBody PieceDto pieceDto) {

        PieceDto addedPieceDto = pieceService.save(pieceDto);

        return ResponseEntity.ok(addedPieceDto);
    }

    @PutMapping()
    public ResponseEntity<?> updatePiece(@Valid @RequestBody PieceDto pieceDto) {

        if(!pieceService.existsById(pieceDto.getId())) return ResponseEntity.notFound().build();

        PieceDto updatedPieceDto = pieceService.update( pieceDto);

        return ResponseEntity.ok(updatedPieceDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePiece(@PathVariable Long id) {
        if(!pieceService.existsById(id)) return ResponseEntity.notFound().build();

        pieceService.deleteById(id);

        return ResponseEntity.ok().build();
    }

}
