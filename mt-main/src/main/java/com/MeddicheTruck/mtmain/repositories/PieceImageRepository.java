package com.MeddicheTruck.mtmain.repositories;

import com.MeddicheTruck.mtmain.entities.PieceImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(exported = false)
public interface PieceImageRepository extends JpaRepository<PieceImage, Long> {

}
