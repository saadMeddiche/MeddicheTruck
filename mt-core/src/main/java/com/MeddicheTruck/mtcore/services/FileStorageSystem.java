package com.MeddicheTruck.mtcore.services;

public interface FileStorageSystem {

    // Store The file then return its path
    String store(byte[] fileInBase64Format, String fileName , String folderName);
}
