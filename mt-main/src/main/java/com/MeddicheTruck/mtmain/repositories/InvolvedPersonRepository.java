package com.MeddicheTruck.mtmain.repositories;

import com.MeddicheTruck.mtmain.entities.InvolvedPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvolvedPersonRepository extends JpaRepository<InvolvedPerson, Long> {}
