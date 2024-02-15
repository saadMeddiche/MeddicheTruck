package com.MeddicheTruck.mtcore.services;

public interface FileStorageService {
    String storeFile(byte[] fileInBase64Format, String fileName , String folderName);
}
