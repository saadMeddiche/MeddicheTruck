package com.MeddicheTruck.mtcore.base;

import com.MeddicheTruck.mtcore.controllers.CustomPageResponse;
import com.MeddicheTruck.mtcore.models.BaseEntity;
import com.MeddicheTruck.mtcore.models.BaseEntityDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BaseServiceInterface<E extends BaseEntity , I_DTO extends BaseEntityDto , O_DTO extends BaseEntityDto> {

    List<O_DTO> saveAll(List<I_DTO> entities);

    O_DTO save(I_DTO entity);

    O_DTO update(I_DTO entity);

    void deleteById(Long id);

    O_DTO findById(Long id);

    E findByIdEntity(Long id);
    
    List<O_DTO> findAll();

    Boolean existsById(Long id);

    public Boolean doesNotExistById(Long id);

    CustomPageResponse<E,O_DTO> dynamicSearch(String searchTerm , Pageable pageable);
}
