package com.MeddicheTruck.mtmain.services.concretes;

import com.MeddicheTruck.mtmain.properties.Domain;
import com.MeddicheTruck.mtmain.properties.StaticContent;
import com.MeddicheTruck.mtmain.properties.interfaces.StorageProperties;
import com.MeddicheTruck.mtmain.services.FileSystemStorageService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PieceImageSystemStorageService extends FileSystemStorageService {
    public PieceImageSystemStorageService(
            @Qualifier("pieceImageStorageProperties") StorageProperties properties,
            StaticContent staticContent,
            Domain domain) {
        super(properties, staticContent, domain);
    }
}