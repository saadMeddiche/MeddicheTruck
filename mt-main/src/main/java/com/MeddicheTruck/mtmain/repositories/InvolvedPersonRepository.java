package com.MeddicheTruck.mtmain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvolvedPersonRepository extends JpaRepository<InvolvedPersonRepository, Long> {}
