package com.MeddicheTruck.mtsecurity.services;

import com.MeddicheTruck.mtsecurity.entities.Authority;

public interface AuthorityService {
    
    Authority getRoleByName(String name);
}
