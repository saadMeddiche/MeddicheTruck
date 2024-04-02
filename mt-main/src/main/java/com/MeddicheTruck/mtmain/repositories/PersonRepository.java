package com.MeddicheTruck.mtmain.repositories;

import com.MeddicheTruck.mtmain.entities.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query("SELECT p FROM Person p WHERE " +
            "p.name.first LIKE %:searchTerm% OR " +
            "p.name.middle LIKE %:searchTerm% OR " +
            "p.name.last LIKE %:searchTerm% OR " +
            "CAST(p.birthDate AS string ) LIKE %:searchTerm% OR " +
            "p.description LIKE %:searchTerm%")
    Page<Person> dynamicSearch(@Param("searchTerm") String searchTerm, Pageable pageable);
}
