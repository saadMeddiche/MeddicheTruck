package com.MeddicheTruck.mtmain.repositories;

import com.MeddicheTruck.mtmain.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{
}
