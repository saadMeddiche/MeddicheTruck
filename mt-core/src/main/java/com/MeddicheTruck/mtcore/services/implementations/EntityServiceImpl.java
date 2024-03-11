package com.MeddicheTruck.mtcore.services.implementations;

import com.MeddicheTruck.mtcore.models.MyEntity;
import com.MeddicheTruck.mtcore.models.MyField;
import com.MeddicheTruck.mtcore.services.EntityService;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Table;
import jakarta.persistence.metamodel.EntityType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class EntityServiceImpl implements EntityService {

    private final EntityManager entityManager;

    @Override
    public List<MyEntity> getAllEntitiesWithPublicSchema() {
        return getAllEntities().stream()
                .map(entity -> new MyEntity(
                        entity.getJavaType().getSimpleName(),
                        getFields(entity)
                ))
                .toList();
    }

    // Return a list of all entities except the ones in the security package
    private List<EntityType<?>> getAllEntities() {
        return entityManager.getMetamodel().getEntities().stream()
                .filter(entity -> entity.getJavaType().getAnnotation(Table.class) == null)
                .toList();
    }

    // return a list of fields for a given entity
    private List<MyField> getFields(EntityType<?> entity) {
        return entity.getAttributes().stream()
                .map(attribute -> new MyField(
                        attribute.getName(),
                        attribute.getJavaType().getSimpleName(),
                        false,
                        null,
                        setFieldId(attribute.getName())
                ))
                .toList();
    }

    private String setFieldId(String name) {
        StringBuilder sb = new StringBuilder();
        sb.append("ID#");
        sb.append(name);
        return sb.toString();
    }
}
