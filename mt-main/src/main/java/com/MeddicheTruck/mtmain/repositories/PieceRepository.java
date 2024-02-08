package com.MeddicheTruck.mtmain.repositories;

import com.MeddicheTruck.mtmain.entities.Piece;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PieceRepository extends JpaRepository<Piece, Long> {
}
