package com.MeddicheTruck.mtcore.base;

import com.MeddicheTruck.mtcore.models.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

@NoRepositoryBean
public interface BaseRepository<E extends BaseEntity> extends JpaRepository<E,Long> {
    Page<E> dynamicSearch(@Param("searchTerm") String searchTerm, Pageable pageable);
}
