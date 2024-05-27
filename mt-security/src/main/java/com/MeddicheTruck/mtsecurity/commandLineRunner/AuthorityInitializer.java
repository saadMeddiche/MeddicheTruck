package com.MeddicheTruck.mtsecurity.commandLineRunner;

import com.MeddicheTruck.mtsecurity.entities.Authority;
import com.MeddicheTruck.mtsecurity.enums.BaseAuthority;
import com.MeddicheTruck.mtsecurity.repositories.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE + 1)
public class AuthorityInitializer implements CommandLineRunner {

    private final AuthorityRepository authorityRepository;

    @Override
    public void run(String... args) throws Exception {
        authorityRepository.saveAll(generateAuthorities());
        System.out.println("Authorities initialized successfully.");
    }

    private List<Authority> generateAuthorities(){
        return Arrays.stream(BaseAuthority.values()).map(Authority::new).toList();
    }
}
