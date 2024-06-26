package com.MeddicheTruck.mtmain.repositories;

import com.MeddicheTruck.mtcore.base.BaseRepository;
import com.MeddicheTruck.mtmain.entities.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends BaseRepository<Person> {
    @Query("SELECT p FROM Person p WHERE " +
            "CAST(p.id AS string ) = :searchTerm OR " +
            "CONCAT(p.firstName, ' ', p.middleName, ' ', p.lastName) LIKE %:searchTerm% OR " +
            "CONCAT(p.firstName, ' ', p.lastName) LIKE %:searchTerm% OR " +
            "p.firstName LIKE %:searchTerm% OR " +
            "p.middleName LIKE %:searchTerm% OR " +
            "p.lastName LIKE %:searchTerm% OR " +
            "p.mainPhoneNumber LIKE %:searchTerm% OR " +
            "p.secondaryPhoneNumber LIKE %:searchTerm% OR " +
            "CAST(p.birthDate AS string ) LIKE %:searchTerm% OR " +
            "p.description LIKE %:searchTerm%")
    Page<Person> dynamicSearch(@Param("searchTerm") String searchTerm, Pageable pageable);

    // For saving a new person
    Boolean existsPersonByMainPhoneNumber(String mainPhoneNumber);

    // For updating a person
    Boolean existsPersonByMainPhoneNumberAndIdNot(String mainPhoneNumber, Long id);
}
