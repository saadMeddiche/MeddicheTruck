package com.MeddicheTruck.mtmain.repositories;

import com.MeddicheTruck.mtcore.base.BaseRepository;
import com.MeddicheTruck.mtmain.entities.VehicleTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleTransactionRepository extends BaseRepository<VehicleTransaction> {

    @Query("SELECT vt FROM VehicleTransaction vt WHERE " +
            "CAST(vt.id AS string ) = :searchTerm OR " +
            "CAST(vt.date AS string) LIKE %:searchTerm% OR "+
            "CAST(vt.time AS string) LIKE %:searchTerm% OR "+
            "CAST(vt.type AS string) LIKE %:searchTerm% OR "+
            "vt.description LIKE %:searchTerm% OR "+
            "vt.vehicle.model LIKE %:searchTerm% OR "+
            "vt.vehicle.plate LIKE %:searchTerm% OR "+
            "CAST(vt.vehicle.type AS string) LIKE %:searchTerm% OR "+
            "CAST(vt.vehicle.engineType AS string ) LIKE %:searchTerm% OR "+
            "vt.person.fullName LIKE %:searchTerm% OR "+
            "CAST(vt.price AS string) LIKE %:searchTerm% ")
    Page<VehicleTransaction> dynamicSearch(@Param("searchTerm") String searchTerm, Pageable pageable);

    @Query("SELECT vt FROM VehicleTransaction vt WHERE " +
            "vt.vehicle.id = :vehicleId AND (" +
            "CAST(vt.id AS string ) = :searchTerm OR " +
            "CAST(vt.date AS string) LIKE %:searchTerm% OR "+
            "CAST(vt.time AS string) LIKE %:searchTerm% OR "+
            "CAST(vt.type AS string) LIKE %:searchTerm% OR "+
            "vt.description LIKE %:searchTerm% OR "+
            "vt.vehicle.model LIKE %:searchTerm% OR "+
            "vt.vehicle.plate LIKE %:searchTerm% OR "+
            "CAST(vt.vehicle.type AS string) LIKE %:searchTerm% OR "+
            "CAST(vt.vehicle.engineType AS string ) LIKE %:searchTerm% OR "+
            "vt.person.fullName LIKE %:searchTerm% OR "+
            "CAST(vt.price AS string) LIKE %:searchTerm% )")
    Page<VehicleTransaction> getVehicleTransactionByVehicle(Long vehicleId, @Param("searchTerm") String searchTerm, Pageable pageable);

    @Query("SELECT vt FROM VehicleTransaction vt WHERE " +
            "vt.person.id = :personId AND (" +
            "CAST(vt.id AS string ) = :searchTerm OR " +
            "CAST(vt.date AS string) LIKE %:searchTerm% OR "+
            "CAST(vt.time AS string) LIKE %:searchTerm% OR "+
            "CAST(vt.type AS string) LIKE %:searchTerm% OR "+
            "vt.description LIKE %:searchTerm% OR "+
            "vt.vehicle.model LIKE %:searchTerm% OR "+
            "vt.vehicle.plate LIKE %:searchTerm% OR "+
            "CAST(vt.vehicle.type AS string) LIKE %:searchTerm% OR "+
            "CAST(vt.vehicle.engineType AS string ) LIKE %:searchTerm% OR "+
            "vt.person.fullName LIKE %:searchTerm% OR "+
            "CAST(vt.price AS string) LIKE %:searchTerm% )")
    Page<VehicleTransaction> getVehicleTransactionByPerson(Long personId, @Param("searchTerm") String searchTerm, Pageable pageable);
}
