package com.MeddicheTruck.mtmain.controllers;

import com.MeddicheTruck.mtcore.controllers.CustomPageResponse;
import com.MeddicheTruck.mtmain.dtos.PieceImageDto;
import com.MeddicheTruck.mtmain.entities.PieceImage;
import com.MeddicheTruck.mtmain.services.PieceImageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pieceImages")
@RequiredArgsConstructor
public class PieceImageController {

    private final PieceImageService pieceImageService;

    private final ModelMapper modelMapper = new ModelMapper();

    @GetMapping()
    public ResponseEntity<?> dynamicSearch(
            @RequestParam(defaultValue = "") String searchTerm ,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        PageRequest pageable = PageRequest.of(page, size);

        return ResponseEntity.ok(pieceImageService.dynamicSearch(searchTerm, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPieceImage(@PathVariable Long id) {
        if(!pieceImageService.existsById(id)) return ResponseEntity.notFound().build();

        PieceImageDto pieceImage = pieceImageService.findById(id);

        return ResponseEntity.ok(pieceImage);
    }

    @PostMapping
    public ResponseEntity<?> createPiece(@Valid @RequestBody PieceImageDto pieceImageDto) {

        PieceImageDto addedPieceImageDto = pieceImageService.save(pieceImageDto);

        return ResponseEntity.ok(addedPieceImageDto);
    }

    @PutMapping()
    public ResponseEntity<?> updatePiece(@Valid @RequestBody PieceImageDto pieceImageDto) {

        if(!pieceImageService.existsById(pieceImageDto.getId())) return ResponseEntity.notFound().build();

        PieceImageDto updatedPieceImageDto = pieceImageService.update(pieceImageDto);

        return ResponseEntity.ok(updatedPieceImageDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePiece(@PathVariable Long id) {
        if(!pieceImageService.existsById(id)) return ResponseEntity.notFound().build();

        pieceImageService.deleteById(id);

        return ResponseEntity.ok().build();
    }

}
