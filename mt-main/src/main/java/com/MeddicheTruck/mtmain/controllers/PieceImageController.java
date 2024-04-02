package com.MeddicheTruck.mtmain.controllers;

import com.MeddicheTruck.mtmain.dtos.PieceImageIDto;
import com.MeddicheTruck.mtmain.dtos.PieceImageODto;
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

        PieceImageODto pieceImage = pieceImageService.findById(id);

        return ResponseEntity.ok(pieceImage);
    }

    @PostMapping
    public ResponseEntity<?> createPieceImage(@Valid @RequestBody PieceImageIDto pieceImageIDto) {

        PieceImageODto addedPieceImageIDto = pieceImageService.save(pieceImageIDto);

        return ResponseEntity.ok(addedPieceImageIDto);
    }

    @PutMapping()
    public ResponseEntity<?> updatePieceImage(@Valid @RequestBody PieceImageIDto pieceImageIDto) {

        if(!pieceImageService.existsById(pieceImageIDto.getId())) return ResponseEntity.notFound().build();

        PieceImageODto updatedPieceImageIDto = pieceImageService.update(pieceImageIDto);

        return ResponseEntity.ok(updatedPieceImageIDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePieceImage(@PathVariable Long id) {
        if(!pieceImageService.existsById(id)) return ResponseEntity.notFound().build();

        pieceImageService.deleteById(id);

        return ResponseEntity.ok().build();
    }

}
