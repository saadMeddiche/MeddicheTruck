## BaseController: Simplifying Controller Logic

### Introduction
In our Spring Boot application, the `BaseController` class serves as a foundation for all controller classes, reducing code repetition and ensuring consistency across the application. It provides generic implementations for common RESTful endpoints, making it easier to manage CRUD operations for various entities.

### Key Features of BaseController

1. **Generic Type Parameters**:
    - The `BaseController` class is defined with four generic type parameters:
        - `E extends BaseEntity`: The entity type.
        - `I_DTO extends BaseEntityDto`: The input DTO (Data Transfer Object) type.
        - `O_DTO extends BaseEntityDto`: The output DTO type.
        - `S extends BaseServiceInterface<E, I_DTO, O_DTO>`: The service interface type.

2. **RESTful Endpoints**:
    - **Dynamic Search**: Handles search requests with pagination support.
      ```java
      @GetMapping()
      public ResponseEntity<?> dynamicSearch(
              @RequestParam(defaultValue = "") String searchTerm,
              @RequestParam(defaultValue = "0") int page,
              @RequestParam(defaultValue = "5") int size) {
          PageRequest pageable = PageRequest.of(page, size);
          return ResponseEntity.ok(service.dynamicSearch(searchTerm, pageable));
      }
      ```
    - **Get by ID**: Retrieves an entity by its ID.
      ```java
      @GetMapping("/{id}")
      public ResponseEntity<?> get(@PathVariable Long id) {
          O_DTO dto = service.findById(id);
          return ResponseEntity.ok(dto);
      }
      ```
    - **Get All**: Retrieves all entities.
      ```java
      @GetMapping("/all")
      public ResponseEntity<?> getAll() {
          return ResponseEntity.ok(service.findAll());
      }
      ```
    - **Create**: Creates a new entity from a DTO.
      ```java
      @PostMapping()
      public ResponseEntity<?> create(@RequestBody @FilterDtoFields I_DTO dto) {
          O_DTO addedDto = service.save(dto);
          return ResponseEntity.ok(addedDto);
      }
      ```
      Supports form data input as well:
      ```java
      @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
      public ResponseEntity<?> createM(@ModelAttribute @FilterDtoFields I_DTO dto) {
          O_DTO addedDto = service.save(dto);
          return ResponseEntity.ok(addedDto);
      }
      ```
    - **Update**: Updates an existing entity from a DTO.
      ```java
      @PutMapping()
      public ResponseEntity<?> update(@RequestBody @FilterDtoFields I_DTO dto) {
          if (!service.existsById(dto.getId())) return ResponseEntity.notFound().build();
          O_DTO updatedDto = service.update(dto);
          return ResponseEntity.ok(updatedDto);
      }
      ```
      Supports form data input as well:
      ```java
      @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
      public ResponseEntity<?> updateM(@ModelAttribute @FilterDtoFields I_DTO dto) {
          if (!service.existsById(dto.getId())) return ResponseEntity.notFound().build();
          O_DTO updatedDto = service.update(dto);
          return ResponseEntity.ok(updatedDto);
      }
      ```
    - **Delete**: Deletes an entity by its ID.
      ```java
      @DeleteMapping("/{id}")
      public ResponseEntity<?> delete(@PathVariable Long id) {
          service.deleteById(id);
          return ResponseEntity.ok().build();
      }
      ```

### Example Usage
Suppose we have an `OrderController` that needs to provide RESTful endpoints for `Order` entities. By extending `BaseController`, we can inherit all the generic methods and focus only on order-specific endpoints if needed.

```java
@RestController
@RequestMapping("/orders")
public class OrderController extends BaseController<Order, OrderInputDto, OrderOutputDto, OrderService> {

    @Autowired
    public OrderController(OrderService service) {
        super(service);
    }

    // Order-specific endpoints can be added here if needed
}
```

With this setup, `OrderController` automatically gains the ability to handle CRUD operations for `Order` entities without writing redundant code.

### Benefits

1. **Code Reusability**: Centralizes common RESTful endpoint logic, reducing redundancy and potential for errors.
2. **Maintainability**: Changes to common functionality only need to be made in one place.
3. **Scalability**: Easily extendable for new entities by creating specific controller classes that extend `BaseController`.
4. **Consistency**: Ensures consistent handling of CRUD operations and validations across different controllers.

### Conclusion
By implementing the `BaseController` class, our Spring Boot application achieves a higher level of code reuse, maintainability, and consistency. This approach allows developers to focus on writing business-specific endpoints while relying on a robust, reusable base for common operations.

This structured approach to handling controller logic not only simplifies development but also ensures that our application remains scalable and maintainable as it grows.
