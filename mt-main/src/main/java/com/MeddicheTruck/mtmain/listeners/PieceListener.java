package com.MeddicheTruck.mtmain.listeners;

import com.MeddicheTruck.mtcore.services.FileStorageService;
import com.MeddicheTruck.mtmain.entities.Piece;
import jakarta.persistence.PrePersist;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component @NoArgsConstructor
public class PieceListener {
    private FileStorageService fss;
    PieceListener(FileStorageService fss) {
        this.fss = fss;
    }
    @PrePersist
    public void prePersist(Piece piece) {

        Optional.ofNullable(piece.getImages())
            .ifPresent(images -> images.forEach(
                    image -> {
                        image.setPiece(piece);
                        image.setPhotoPath(fss.storeFile(image.getPhotoInBase64Format(), image.getName() , "pieces"));
                    }
            ));

    }
}
