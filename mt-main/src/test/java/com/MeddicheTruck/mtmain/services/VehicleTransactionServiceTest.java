package com.MeddicheTruck.mtmain.services;

import com.MeddicheTruck.mtcore.handlingExceptions.costumExceptions.AlreadyExistsException;
import com.MeddicheTruck.mtcore.handlingExceptions.costumExceptions.DoNotExistException;
import com.MeddicheTruck.mtmain.dtos.VehicleTransactionIDto;
import com.MeddicheTruck.mtmain.entities.Vehicle;
import com.MeddicheTruck.mtmain.entities.VehicleTransaction;
import com.MeddicheTruck.mtmain.enums.EngineType;
import com.MeddicheTruck.mtmain.enums.TransactionType;
import com.MeddicheTruck.mtmain.enums.VehicleType;
import com.MeddicheTruck.mtmain.repositories.VehicleTransactionRepository;
import com.MeddicheTruck.mtmain.services.implementations.VehicleTransactionServiceImpl;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class VehicleTransactionServiceTest {

    @Mock
    private VehicleTransactionRepository repository;

    @Mock
    private VehicleService vehicleService;

    @Mock
    private PersonService personService;

    @InjectMocks
    private VehicleTransactionServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    class SellingTransactionTests {

        @Test
        public void shouldSaveVehicleTransaction_WhenVehicleInStock() {

            when(vehicleService.isInStock(anyLong())).thenReturn(true);

            when(repository.save(any())).thenReturn(createValidVehicleTransaction(1L , TransactionType.SELL));

            when(vehicleService.findByIdEntity(anyLong())).thenReturn(createValidVehicle(1L , false));

             service.save(createValidVehicleTransactionDto(null , TransactionType.SELL));

        }

        @Test
        public void shouldThrowDoNotExistException_WhenVehicleNotInStock() {

            when(vehicleService.isNotInStock(anyLong())).thenReturn(true);

            assertThrows(DoNotExistException.class, () -> service.save(createValidVehicleTransactionDto(null , TransactionType.SELL)));

        }

        @Test
        public void inStockShouldBeFalse_WhenSellingTransactionHappens() {

            Vehicle vehicle = createValidVehicle(1L , true);
            when(vehicleService.findByIdEntity(anyLong())).thenReturn(vehicle);

            service.afterSave(createValidVehicleTransaction(1L , TransactionType.SELL) , createValidVehicleTransactionDto(null, TransactionType.SELL));

            assertFalse(vehicle.getInStock());

        }

    }

    @Nested
    class BuyingTransactionTests {

        @Test
        public void shouldSaveVehicleTransaction_WhenVehicleNotInStock() {

            when(vehicleService.isNotInStock(anyLong())).thenReturn(true);

            when(repository.save(any())).thenReturn(createValidVehicleTransaction(1L , TransactionType.BUY));

            when(vehicleService.findByIdEntity(anyLong())).thenReturn(createValidVehicle(1L , false));

            service.save(createValidVehicleTransactionDto(null , TransactionType.BUY));

        }

        @Test
        public void shouldThrowAlreadyExistsException_WhenVehicleInStock() {

            when(vehicleService.isInStock(anyLong())).thenReturn(true);

            assertThrows(AlreadyExistsException.class, () -> service.save(createValidVehicleTransactionDto(null , TransactionType.BUY)));

        }

        @Test
        public void inStockShouldReturnTrue_WhenBuyingTransactionHappens() {

                Vehicle vehicle = createValidVehicle(1L , false);
                when(vehicleService.findByIdEntity(anyLong())).thenReturn(vehicle);

                service.afterSave(createValidVehicleTransaction(1L , TransactionType.BUY) , createValidVehicleTransactionDto(null, TransactionType.BUY));

                assertTrue(vehicle.getInStock());
        }

    }

    /* globalValidationTests means validation that is not specific to a transaction type,
    * and validations that should be done before the save or update methods */
    @Nested
    class globalValidationTests {

        @Test
        public void shouldThrowDoNotExistException_WhenVehicleDoNotExist() {
            when(vehicleService.doesNotExistById(anyLong())).thenReturn(true);

            assertThrows(DoNotExistException.class, () -> service.save(createValidVehicleTransactionDto(null , TransactionType.BUY) ));
        }

        @Test
        public void shouldThrowDoNotExistException_WhenPersonDoNotExist() {
            when(personService.doesNotExistById(anyLong())).thenReturn(true);

            assertThrows(DoNotExistException.class, () -> service.save(createValidVehicleTransactionDto(null , TransactionType.BUY) ));
        }

    }

    @Nested
    class dtoValidationsForSavingASellingTransaction {

        @TestFactory
        public Stream<DynamicTest> shouldThrowValidationException_WhenDtoIsInvalidOnSaving() throws NoSuchFieldException {
            return invalidDtoListForSaving().stream().map(dto -> DynamicTest.dynamicTest(dto.toString(),
                    () -> assertThrows(ConstraintViolationException.class, () -> service.save(dto)))
            );

        }

    }


    private List<VehicleTransactionIDto> invalidDtoListForSaving () throws NoSuchFieldException {

        List<String> excludedFieldsNames = List.of("id" , "description");

        // Null values
        List<VehicleTransactionIDto> invalidDtoList =
                generateInvalidDtoList(this::createSampleDtoForSaving , excludedFieldsNames);

        // Negative price
        invalidDtoList.add(
                VehicleTransactionIDto.builder()
                .id(null)
                .date(LocalDate.now())
                .time(LocalTime.now())
                .description("description")
                .type("BUY")
                .vehicleId(1L)
                .personId(1L)
                .price(-100.0).build()
        );

        // Invalid type
        invalidDtoList.add(
                VehicleTransactionIDto.builder()
                        .id(null)
                        .date(LocalDate.now())
                        .time(LocalTime.now())
                        .description("description")
                        .type("NOT_VALID_TYPE")
                        .vehicleId(1L)
                        .personId(1L)
                        .price(100.0).build()
        );

        return invalidDtoList;
    }

    private List<VehicleTransactionIDto> generateInvalidDtoList (Supplier<VehicleTransactionIDto> createSimpleDto , List<String> excludedFieldsNames) {

        List<Field> excludedFields = excludedFieldsNames.stream().map(
                fieldName -> {
                    try {
                        Field field = VehicleTransactionIDto.class.getDeclaredField(fieldName);
                        field.setAccessible(true);
                        return field;
                    } catch (NoSuchFieldException e) {
                        throw new RuntimeException(e);
                    }
                }
        ).toList();

        List<VehicleTransactionIDto> invalidDtoList = new ArrayList<>();

        Field[] fields = VehicleTransactionIDto.class.getDeclaredFields();




        Arrays.stream(fields)
                .filter(field -> !excludedFields.contains(field))
                .forEach(field -> {

            VehicleTransactionIDto invalidDto = createSimpleDto.get();

            field.setAccessible(true);

            try {
                field.set(invalidDto, null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            invalidDtoList.add(invalidDto);
        });

        return invalidDtoList;
    }

    private VehicleTransactionIDto createSampleDtoForSaving() {
        return VehicleTransactionIDto.builder()
                .id(null)
                .date(LocalDate.now())
                .time(LocalTime.now())
                .description("description")
                .type("BUY")
                .vehicleId(1L)
                .personId(1L)
                .price(100.0).build();
    }

    private VehicleTransaction createValidVehicleTransaction(Long id , TransactionType type) {
        VehicleTransaction dto = new VehicleTransaction();
        dto.setId(id);
        dto.setDate(LocalDate.now());
        dto.setTime(LocalTime.now());
        dto.setDescription("description");
        dto.setType(type);
        dto.setPrice(100.0);
        return dto;
    }

    private VehicleTransactionIDto createValidVehicleTransactionDto(Long id , TransactionType type) {
        return VehicleTransactionIDto.builder()
                .id(id)
                .date(LocalDate.now())
                .time(LocalTime.now())
                .description("description")
                .type(type.toString())
                .vehicleId(1L)
                .personId(1L)
                .price(100.0).build();
    }

    private Vehicle createValidVehicle(Long id , Boolean inStock) {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(id);
        vehicle.setPlate("plate");
        vehicle.setModel("model");
        vehicle.setType(VehicleType.TRUCK);
        vehicle.setEngineType(EngineType.DIESEL);
        vehicle.setInStock(inStock);
        return vehicle;
    }

}
