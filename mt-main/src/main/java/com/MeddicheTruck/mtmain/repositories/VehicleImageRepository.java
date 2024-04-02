package com.MeddicheTruck.mtmain.repositories;

import com.MeddicheTruck.mtmain.entities.VehicleImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleImageRepository extends JpaRepository<VehicleImage, Long> {
}
