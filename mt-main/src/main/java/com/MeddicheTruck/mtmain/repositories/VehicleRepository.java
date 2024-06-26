package com.MeddicheTruck.mtmain.repositories;

import com.MeddicheTruck.mtcore.base.BaseRepository;
import com.MeddicheTruck.mtmain.entities.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends BaseRepository<Vehicle> {

    @Query("SELECT v FROM Vehicle v WHERE" +
            " CAST(v.id AS string ) = :searchTerm OR" +
            " v.model LIKE %:searchTerm% OR" +
            " v.plate LIKE %:searchTerm% OR" +
            " CAST(v.type AS string) LIKE %:searchTerm% OR" +
            " CAST(v.engineType AS string) LIKE %:searchTerm%")
    Page<Vehicle> dynamicSearch(@Param("searchTerm") String searchTerm, Pageable pageable);

    @Query("SELECT v.inStock FROM Vehicle v WHERE v.id = :id")
    Boolean isInStock(@Param("id") Long id);

    Boolean existsVehicleByPlate(String plate);

    Boolean existsVehicleByPlateAndIdNot(String plate, Long id);
}
