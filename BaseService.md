### Code Reusability with BaseService

---

#### Introduction
In our Spring Boot application, we've implemented a `BaseService` class that significantly reduces code repetition across our service layer. This class encapsulates common CRUD (Create, Read, Update, Delete) operations and validation logic, allowing us to focus on specific business logic without rewriting boilerplate code.

---

#### Key Features of BaseService

1. **Generic Type Parameters**:
    - The `BaseService` class is defined with four generic type parameters:
        - `E extends BaseEntity`: The entity type.
        - `I_DTO extends BaseEntityDto`: The input DTO type.
        - `O_DTO extends BaseEntityDto`: The output DTO type.
        - `R extends BaseRepository<E>`: The repository type.

2. **ModelMapper Integration**:
    - Utilizes `ModelMapper` to handle the conversion between entities and DTOs.
    - This reduces the need for manual mapping, streamlining the process of converting data between layers.

3. **CRUD Operations**:
    - **Save**: Converts DTOs to entities, performs validation, and saves them to the repository.
    - **Update**: Similar to save, but also includes a step to fetch the existing entity and update its properties.
    - **Delete**: Validates the ID and deletes the entity by its ID.
    - **FindById**: Retrieves an entity by its ID and maps it to a DTO.
    - **FindAll**: Retrieves all entities and maps them to DTOs.
    - **Dynamic Search**: Supports custom search operations with pagination.

4. **Validation Methods**:
    - **Global Validation**: Ensures that the DTO is valid before processing.
    - **ID Validations**: Checks if the ID is null or does not exist.
    - **Entity Existence Validation**: Ensures that the entity exists before performing operations.

5. **Lifecycle Hooks**:
    - **Before Save/Update/Delete**: Methods (`beforeSave`, `beforeUpdate`, `beforeDelete`) can be overridden to add custom logic before these operations.
    - **After Save/Update/Delete**: Method (`afterSave`, `afterUpdate`, `afterDelete`) can be overridden to add custom logic after these operations.

---

#### Example Usage
Suppose we have an `OrderService` that needs to perform standard CRUD operations for `Order` entities. By extending `BaseService`, we can inherit all the generic methods and focus only on order-specific logic.

```java
@Service
public class OrderService extends BaseService<Order, OrderInputDto, OrderOutputDto, OrderRepository> {

    @Autowired
    public OrderService(OrderRepository repository) {
        super(repository, Order.class, OrderInputDto.class, OrderOutputDto.class);
    }

    @Override
    protected String recordName() {
        return "Order";
    }

    @Override
    public void beforeSave(Order entityToSave, OrderInputDto entityDto) {
        // Add custom logic before saving an order
    }

    @Override
    public void afterSave(Order savedEntity, OrderInputDto entityDto) {
        // Add custom logic after saving an order
    }

    @Override
    public void saveValidation(OrderInputDto entityDto) {
        // Add custom logic for validating order input
    }
}
```

With this setup, `OrderService` automatically gains the ability to save, update, delete, and find `Order` entities without writing redundant code.

---

#### Benefits

1. **Code Reusability**: Centralizes common logic, reducing redundancy and the potential for errors.
2. **Maintainability**: Changes to common functionality only need to be made in one place.
3. **Scalability**: Easily extendable for new entities by creating specific service classes that extend `BaseService`.
4. **Consistency**: Ensures consistent handling of CRUD operations and validations across different services.

---

#### Conclusion
By implementing the `BaseService` class, our Spring Boot application achieves a higher level of code reuse, maintainability, and consistency. This approach allows developers to focus on writing business-specific logic while relying on a robust, reusable base for common operations.

---

This structured approach to handling service operations not only simplifies development but also ensures that our application remains scalable and maintainable as it grows.