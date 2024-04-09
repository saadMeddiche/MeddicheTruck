package com.MeddicheTruck.mtcore.base;

import com.MeddicheTruck.mtcore.annotations.FilterDtoFields;
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
    public ResponseEntity<?> get(@PathVariable Long id) {

        O_DTO dto = service.findById(id);

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @FilterDtoFields I_DTO dto) {

        O_DTO addedDto = service.save(dto);

        return ResponseEntity.ok(addedDto);
    }

    @PutMapping()
    public ResponseEntity<?> update(@RequestBody @FilterDtoFields I_DTO dto) {

        if(!service.existsById(dto.getId())) return ResponseEntity.notFound().build();

        O_DTO updatedDto = service.update(dto);

        return ResponseEntity.ok(updatedDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        service.deleteById(id);

        return ResponseEntity.ok().build();
    }

}
