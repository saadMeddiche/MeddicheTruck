package com.MeddicheTruck.mtmain.repositories;

import com.MeddicheTruck.mtmain.entities.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{
    @Query("SELECT t FROM Transaction t WHERE " +
            "t.name LIKE %:searchTerm% OR " +
            "t.Description LIKE %:searchTerm% OR " +
            "CAST(t.timeTransaction AS string) LIKE %:searchTerm% OR " +
            "CAST(t.type AS string) LIKE %:searchTerm%")
    Page<Transaction> dynamicSearch(@Param("searchTerm") String searchTerm, Pageable pageable);
}
