package com.MeddicheTruck.mtmain.repositories;

import com.MeddicheTruck.mtmain.entities.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query("SELECT v FROM Vehicle v WHERE" +
            " v.model LIKE %:searchTerm% OR" +
            " v.plate LIKE %:searchTerm% OR" +
            " CAST(v.type AS string) LIKE %:searchTerm% OR" +
            " CAST(v.engineType AS string) LIKE %:searchTerm%")
    Page<Vehicle> dynamicSearch(@Param("searchTerm") String searchTerm, Pageable pageable);
}
