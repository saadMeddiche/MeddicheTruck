package com.MeddicheTruck.mtcore.base;

import com.MeddicheTruck.mtcore.models.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BaseServiceInterface<E extends BaseEntity> {
    
    List<E> saveAll(List<Long> ids);
    
    E save(E entity);
    
    E update(E entity);
    
    void delete(E entity);

    void deleteById(Long id);
    
    E findById(Long id);
    
    List<E> findAll();

    Boolean existsById(Long id);

    Page<E> dynamicSearch(String searchTerm , Pageable pageable);
}
