package com.MeddicheTruck.mtmain.repositories;

import com.MeddicheTruck.mtcore.base.BaseRepository;
import com.MeddicheTruck.mtmain.entities.PhoneNumber;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PhoneNumberRepository extends BaseRepository<PhoneNumber>{

    @Query("SELECT p FROM PhoneNumber p WHERE" +
            " p.id = :id OR" +
            " p.number LIKE %:searchTerm% OR" +
            " CAST(p.type AS string ) LIKE %:searchTerm% OR" +
            " p.person.firstName LIKE %:searchTerm% OR" +
            " p.person.lastName LIKE %:searchTerm% OR" +
            " p.person.middleName  LIKE %:searchTerm%"
    )
    Page<PhoneNumber> dynamicSearch(@Param("searchTerm") String searchTerm, Pageable pageable);

    @Query("SELECT p FROM PhoneNumber p WHERE" +
            " p.person.id = :personId AND (" +
            " p.number LIKE %:searchTerm% OR" +
            " CAST(p.type AS string ) LIKE %:searchTerm% OR" +
            " p.person.firstName LIKE %:searchTerm% OR" +
            " p.person.lastName LIKE %:searchTerm% OR" +
            " p.person.middleName  LIKE %:searchTerm% )"
    )
    Page<PhoneNumber> findPhoneNumbersByPersonId(Long personId ,@Param("searchTerm") String searchTerm, Pageable pageable);

    Boolean existsPhoneNumberByNumber(String number);

    Boolean existsPhoneNumberByNumberAndIdNot(String number, Long id);

}
