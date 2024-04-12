package com.MeddicheTruck.mtmain.repositories;

import com.MeddicheTruck.mtcore.base.BaseRepository;
import com.MeddicheTruck.mtmain.entities.PieceImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PieceImageRepository extends BaseRepository<PieceImage> {

    @Override
    @Query("SELECT p FROM PieceImage p WHERE" +
            " p.id = :id OR" +
            " p.name LIKE %:searchTerm%")
    Page<PieceImage> dynamicSearch(@Param("searchTerm") String searchTerm, Pageable pageable);

    @Query("SELECT p FROM PieceImage p WHERE p.piece.id = :pieceId AND p.name LIKE %:searchTerm%")
    Page<PieceImage> findPieceImagesByPieceId(Long pieceId ,@Param("searchTerm") String searchTerm, Pageable pageable);
}
