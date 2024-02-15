package com.MeddicheTruck.mtmain.listeners;

import com.MeddicheTruck.mtcore.services.FileStorageService;
import com.MeddicheTruck.mtmain.entities.Piece;
import jakarta.persistence.PrePersist;

public class PieceListener {

    @PrePersist
    public void prePersist(Piece piece) {
        piece.getImages().forEach(image -> {
            image.setPiece(piece);
            image.setPhotoPath(FileStorageService.storeFile(image.getPhotoInBase64Format(), image.getName() , "pieces"));
        });
    }
}
