package com.MeddicheTruck.mtcore.base;

import com.MeddicheTruck.mtcore.controllers.CustomPageResponse;
import com.MeddicheTruck.mtcore.models.BaseEntity;
import com.MeddicheTruck.mtcore.models.BaseEntityDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BaseServiceInterface<E extends BaseEntity , DTO extends BaseEntityDto> {

    List<DTO> saveAll(List<DTO> entities);
    
    DTO save(DTO entity);
    
    DTO update(DTO entity);

    void deleteById(Long id);
    
    DTO findById(Long id);
    
    List<DTO> findAll();

    Boolean existsById(Long id);

    CustomPageResponse<E,DTO> dynamicSearch(String searchTerm , Pageable pageable);
}
