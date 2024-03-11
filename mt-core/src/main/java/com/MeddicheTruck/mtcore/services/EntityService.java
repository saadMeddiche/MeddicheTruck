package com.MeddicheTruck.mtcore.services;

import com.MeddicheTruck.mtcore.models.MyEntity;

import java.util.List;

public interface EntityService {

    List<MyEntity> getAllEntitiesWithPublicSchema();
}
