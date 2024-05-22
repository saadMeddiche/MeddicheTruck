package com.MeddicheTruck.mtmain.properties;

import java.util.List;

public interface StorageProperties {
    String getDirectory();

    void setDirectory(String directory);

    List<String> getAllowedExtensions();

    void setAllowedExtensions(List<String> extensions);

    String getMaxFileSize();

    void setMaxFileSize(String maxFileSize);
}
