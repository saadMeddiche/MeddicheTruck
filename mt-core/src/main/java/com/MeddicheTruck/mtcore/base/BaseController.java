package com.MeddicheTruck.mtcore.base;

import com.MeddicheTruck.mtcore.models.BaseEntity;
import com.MeddicheTruck.mtcore.models.BaseEntityDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public abstract class BaseController<E extends BaseEntity,I_DTO extends BaseEntityDto, O_DTO extends BaseEntityDto , S extends BaseServiceInterface<E ,I_DTO ,O_DTO> > {

    protected final S service;

    protected BaseController(S service){
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<?> dynamicSearch(
            @RequestParam(defaultValue = "") String searchTerm ,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        PageRequest pageable = PageRequest.of(page, size);

        return ResponseEntity.ok(service.dynamicSearch(searchTerm, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPiece(@PathVariable Long id) {
        if(!service.existsById(id)) return ResponseEntity.notFound().build();

        O_DTO pieceDto = service.findById(id);

        return ResponseEntity.ok(pieceDto);
    }

    @PostMapping
    public ResponseEntity<?> createPiece(@Valid @RequestBody I_DTO pieceDto) {

        O_DTO addedPieceDto = service.save(pieceDto);

        return ResponseEntity.ok(addedPieceDto);
    }

    @PutMapping()
    public ResponseEntity<?> updatePiece(@Valid @RequestBody I_DTO pieceDto) {

        if(!service.existsById(pieceDto.getId())) return ResponseEntity.notFound().build();

        O_DTO updatedPieceDto = service.update(pieceDto);

        return ResponseEntity.ok(updatedPieceDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePiece(@PathVariable Long id) {
        if(!service.existsById(id)) return ResponseEntity.notFound().build();

        service.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
