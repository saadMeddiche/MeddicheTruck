## `@AdaptedDto`: Enhancing DTO Handling

### Introduction
In our Spring Boot application, we use the `@AdaptedDto` annotation to manage how data transfer objects (DTOs) are handled across different request types. The `VehicleImageIDto` class exemplifies this approach, providing a structured way to manage vehicle image data with specific field inclusion rules for various HTTP requests.

### Key Features of `@AdaptedDto` and `VehicleImageIDto`

1. **Annotations for Field Inclusion**:
    - **@AdaptedDto**: Marks the class as an adapted DTO, which is processed by custom logic to include or exclude fields based on the request type.
    - **@IncludeOnAllRequests**: Indicates that the annotated field should be included in all types of requests.
    - **@IncludeOnPostRequest**: Indicates that the annotated field should be included only in POST requests.
    - **@IncludeOnPutRequest**: Indicates that the annotated field should be included only in PUT requests.

### `VehicleImageIDto` Example

```java
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@AdaptedDto
public class VehicleImageIDto extends BaseEntityDto {

    @IncludeOnPutRequest
    protected Long id;

    @IncludeOnAllRequests
    @NotNull(message = "The name of the vehicle image can not be null")
    @NotBlank(message = "The name of the vehicle image can not be blank")
    protected String name;

    @IncludeOnPostRequest
    @NotNull(message = "The id of the vehicle can not be null")
    private Long vehicleId;

    @IncludeOnPostRequest
    @NotNull(message = "The photo of the vehicle can not be null")
    protected MultipartFile photo;
}
```