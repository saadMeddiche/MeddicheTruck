package com.MeddicheTruck.mtmain.listeners;

import com.MeddicheTruck.mtcore.services.FileStorageService;
import com.MeddicheTruck.mtcore.services.Naming;
import com.MeddicheTruck.mtmain.entities.Piece;
import jakarta.persistence.PrePersist;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component @NoArgsConstructor
public class PieceListener {
    private FileStorageService fss;
    private Naming n;

    @Autowired
    PieceListener(FileStorageService fss , Naming n) {
        this.fss = fss;
        this.n = n;
    }
    @PrePersist
    public void prePersist(Piece piece) {

        Optional.ofNullable(piece.getImages())
            .ifPresent(images -> images.forEach(
                    image -> {
                        image.setName(n.uniquifyWord(image.getName()));
                        image.setPiece(piece);
                        image.setPhotoPath(fss.storeFile(image.getPhotoInBase64Format(), image.getName() , "pieces"));
                    }
            ));

    }
}
