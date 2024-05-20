package com.MeddicheTruck.mtmain.repositories;

import com.MeddicheTruck.mtcore.base.BaseRepository;
import com.MeddicheTruck.mtmain.entities.PieceTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PieceTransactionRepository extends BaseRepository<PieceTransaction> {

    @Query("SELECT pt FROM PieceTransaction pt WHERE " +
            "CAST(pt.id AS string ) = :searchTerm OR " +
            "CAST(pt.date AS string) LIKE %:searchTerm% OR "+
            "CAST(pt.time AS string) LIKE %:searchTerm% OR "+
            "CAST(pt.type AS string) LIKE %:searchTerm% OR "+
            "pt.description LIKE %:searchTerm% OR "+
            "pt.piece.name LIKE %:searchTerm% OR "+
            "pt.person.fullName LIKE %:searchTerm% OR "+
            "CAST(pt.price AS string) LIKE %:searchTerm% ")
    Page<PieceTransaction> dynamicSearch(@Param("searchTerm") String searchTerm, Pageable pageable);

    @Query("SELECT pt FROM PieceTransaction pt WHERE " +
            "pt.piece.id = :pieceId AND (" +
            "CAST(pt.id AS string ) = :searchTerm OR " +
            "CAST(pt.date AS string) LIKE %:searchTerm% OR "+
            "CAST(pt.time AS string) LIKE %:searchTerm% OR "+
            "CAST(pt.type AS string) LIKE %:searchTerm% OR "+
            "pt.description LIKE %:searchTerm% OR "+
            "pt.piece.name LIKE %:searchTerm% OR "+
            "pt.person.fullName LIKE %:searchTerm% OR "+
            "CAST(pt.price AS string) LIKE %:searchTerm% )")
    Page<PieceTransaction> getPieceTransactionByPiece(Long pieceId, @Param("searchTerm") String searchTerm, Pageable pageable);

    @Query("SELECT pt FROM PieceTransaction pt WHERE " +
            "pt.person.id = :personId AND (" +
            "CAST(pt.id AS string ) = :searchTerm OR " +
            "CAST(pt.date AS string) LIKE %:searchTerm% OR "+
            "CAST(pt.time AS string) LIKE %:searchTerm% OR "+
            "CAST(pt.type AS string) LIKE %:searchTerm% OR "+
            "pt.description LIKE %:searchTerm% OR "+
            "pt.piece.name LIKE %:searchTerm% OR "+
            "pt.person.fullName LIKE %:searchTerm% OR "+
            "CAST(pt.price AS string) LIKE %:searchTerm% )")
    Page<PieceTransaction> getPieceTransactionByPerson(Long personId, @Param("searchTerm") String searchTerm, Pageable pageable);
}
