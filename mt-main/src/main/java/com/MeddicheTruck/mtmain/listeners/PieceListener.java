package com.MeddicheTruck.mtmain.listeners;

import com.MeddicheTruck.mtmain.entities.Piece;
import jakarta.persistence.PrePersist;

public class PieceListener {

    @PrePersist
    public void prePersist(Object object) {
        System.out.println("PrePersist: " + object.toString());
    }
}
