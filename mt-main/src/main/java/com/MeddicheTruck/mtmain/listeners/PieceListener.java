package com.MeddicheTruck.mtmain.listeners;

import com.MeddicheTruck.mtcore.services.FileStorageSystem;
import com.MeddicheTruck.mtcore.services.Naming;
import com.MeddicheTruck.mtmain.entities.Piece;
import jakarta.persistence.PrePersist;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component @NoArgsConstructor
public class PieceListener {
    private FileStorageSystem fss;
    private Naming n;

    @Autowired
    PieceListener(FileStorageSystem fss , Naming n) {
        this.fss = fss;
        this.n = n;
    }
    @PrePersist
    public void prePersist(Piece piece) {

        Optional.ofNullable(piece.getImages())
            .ifPresent(images -> images.forEach(
                    image -> {
                        image.setPiece(piece);
                        image.setPhotoPath(fss.store(image.getPhotoInBase64Format(),
                                n.uniquifyWord(image.getName()) ,
                                "pieces"));
                    }
            ));

    }
}
