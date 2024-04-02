package com.MeddicheTruck.mtmain.configurations;

import com.MeddicheTruck.mtmain.entities.Piece;
import com.MeddicheTruck.mtmain.entities.PieceImage;
import com.MeddicheTruck.mtmain.entities.Vehicle;
import com.MeddicheTruck.mtmain.entities.VehicleImage;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.RepositoryDetectionStrategy;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

// https://www.baeldung.com/spring-data-rest-serialize-entity-id
@Configuration
public class RestConfiguration implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config , CorsRegistry cors) {
        config.exposeIdsFor(Piece.class);
        config.exposeIdsFor(PieceImage.class);
        config.exposeIdsFor(Vehicle.class);
        config.exposeIdsFor(VehicleImage.class);
        config.setRepositoryDetectionStrategy(RepositoryDetectionStrategy.RepositoryDetectionStrategies.ANNOTATED);
    }
}
