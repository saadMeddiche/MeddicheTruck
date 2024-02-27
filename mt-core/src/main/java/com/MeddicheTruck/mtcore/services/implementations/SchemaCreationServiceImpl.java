package com.MeddicheTruck.mtcore.services.implementations;

import com.MeddicheTruck.mtcore.services.SchemaCreationService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@NoArgsConstructor
public class SchemaCreationServiceImpl implements SchemaCreationService {

    @PersistenceContext
    private EntityManager entityManager;

    public void createTenantForUser(String schemaName){
        schemaName = "tenant_" + schemaName.toLowerCase();
        createSchema(schemaName);
        duplicateTables("public" , schemaName);
    }

    private void createSchema(String schemaName) {
        entityManager.createNativeQuery("CREATE SCHEMA " + schemaName).executeUpdate();
    }

    private void duplicateTables(String originalSchema , String newSchema){

        String selectTablesQuery = "SELECT table_name FROM information_schema.tables WHERE table_schema = :origin";
        Query query = entityManager.createNativeQuery(selectTablesQuery);
        query.setParameter("origin", originalSchema);

        query.getResultList().forEach(table -> {
            String createTableQuery = "CREATE TABLE " + newSchema + "." + table + " AS TABLE " + originalSchema + "." + table;
            entityManager.createNativeQuery(createTableQuery).executeUpdate();
        });



    }
}
