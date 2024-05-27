package com.MeddicheTruck.mtsecurity.repositories;


import com.MeddicheTruck.mtsecurity.entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    Authority findByName(String name);
}
