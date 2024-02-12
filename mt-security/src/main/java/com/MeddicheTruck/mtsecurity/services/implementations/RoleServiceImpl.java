package com.MeddicheTruck.mtsecurity.services.implementations;

import com.MeddicheTruck.mtsecurity.entities.Role;
import com.MeddicheTruck.mtsecurity.repositories.RoleRepository;

import com.MeddicheTruck.mtsecurity.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

   private final RoleRepository roleRepository;

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.findByName(name);
    }
}
