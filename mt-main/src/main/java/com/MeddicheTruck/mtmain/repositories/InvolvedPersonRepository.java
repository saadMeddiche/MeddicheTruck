package com.MeddicheTruck.mtmain.repositories;

import com.MeddicheTruck.mtcore.base.BaseRepository;
import com.MeddicheTruck.mtmain.entities.InvolvedPerson;
import com.MeddicheTruck.mtmain.entities.Person;
import com.MeddicheTruck.mtmain.entities.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InvolvedPersonRepository extends BaseRepository<InvolvedPerson> {

    @Query("SELECT ip FROM InvolvedPerson ip WHERE" +
            " ip.person.firstName LIKE %:searchTerm% OR" +
            " ip.person.lastName LIKE %:searchTerm% OR" +
            " ip.person.middleName LIKE %:searchTerm% OR" +
            " ip.transaction.name LIKE %:searchTerm% OR" +
            " ip.transaction.description LIKE %:searchTerm%")
    Page<InvolvedPerson> dynamicSearch(@Param("searchTerm") String searchTerm, Pageable pageable);

    @Query("SELECT ip.transaction FROM InvolvedPerson ip WHERE ip.person.id = :id")
    Page<Transaction> findTransactionsByPersonId(Long id , Pageable pageable);

    @Query("SELECT ip.person  FROM InvolvedPerson ip WHERE ip.transaction.id = :id")
    Page<Person> findPersonsByTransactionId(Long id , Pageable pageable);
}
