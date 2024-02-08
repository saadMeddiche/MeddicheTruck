package com.MeddicheTruck.mtmain.repositories;

import com.MeddicheTruck.mtmain.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
