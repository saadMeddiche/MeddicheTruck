package com.MeddicheTruck.mtsecurity.services.implementations;

import com.MeddicheTruck.mtsecurity.entities.Authority;
import com.MeddicheTruck.mtsecurity.repositories.AuthorityRepository;

import com.MeddicheTruck.mtsecurity.services.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {

   private final AuthorityRepository authorityRepository;

    @Override
    public Authority getRoleByName(String name) {
        return authorityRepository.findByName(name);
    }
}
