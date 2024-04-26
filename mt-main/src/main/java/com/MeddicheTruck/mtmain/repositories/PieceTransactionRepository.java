package com.MeddicheTruck.mtmain.repositories;

import com.MeddicheTruck.mtcore.base.BaseRepository;
import com.MeddicheTruck.mtmain.entities.PieceTransaction;
import com.MeddicheTruck.mtmain.entities.VehicleTransaction;
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
            "pt.piece.name LIKE %:searchTerm% OR "+
            "pt.person.fullName LIKE %:searchTerm% OR "+
            "CAST(pt.price AS string) LIKE %:searchTerm% ")
    Page<PieceTransaction> dynamicSearch(@Param("searchTerm") String searchTerm, Pageable pageable);
}
