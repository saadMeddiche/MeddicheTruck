package com.MeddicheTruck.mtmain.repositories;

import com.MeddicheTruck.mtmain.entities.Piece;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
public interface PieceRepository extends JpaRepository<Piece, Long> {
    @Query("SELECT p FROM Piece p WHERE p.name LIKE %:searchTerm%")
    Page<Piece> dynamicSearch(@Param("searchTerm") String searchTerm, Pageable pageable);
}
