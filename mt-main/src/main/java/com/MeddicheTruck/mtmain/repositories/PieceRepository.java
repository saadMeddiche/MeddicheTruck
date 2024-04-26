package com.MeddicheTruck.mtmain.repositories;

import com.MeddicheTruck.mtcore.base.BaseRepository;
import com.MeddicheTruck.mtmain.entities.Piece;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface PieceRepository extends BaseRepository<Piece> {
    @Override
    @Query("SELECT p FROM Piece p WHERE" +
            " CAST(p.id AS string ) = :searchTerm OR" +
            " p.name LIKE %:searchTerm%")
    Page<Piece> dynamicSearch(@Param("searchTerm") String searchTerm, Pageable pageable);

    @Query("SELECT p.inStock FROM Piece p WHERE p.id = :id")
    Boolean isInStock(@Param("id") Long id);

}
