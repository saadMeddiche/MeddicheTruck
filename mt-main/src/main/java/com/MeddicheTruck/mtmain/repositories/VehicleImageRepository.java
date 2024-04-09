package com.MeddicheTruck.mtmain.repositories;

import com.MeddicheTruck.mtcore.base.BaseRepository;
import com.MeddicheTruck.mtmain.entities.PieceImage;
import com.MeddicheTruck.mtmain.entities.VehicleImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleImageRepository extends BaseRepository<VehicleImage> {
    @Override
    @Query("SELECT v FROM VehicleImage v WHERE v.name LIKE %:searchTerm%")
    Page<VehicleImage> dynamicSearch(@Param("searchTerm") String searchTerm, Pageable pageable);

    @Query("SELECT v FROM VehicleImage v WHERE v.vehicle.id = :vehicleId AND v.name LIKE %:searchTerm%")
    Page<VehicleImage> findVehicleImageByVehicleId(Long vehicleId ,@Param("searchTerm") String searchTerm, Pageable pageable);
}
