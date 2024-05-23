package com.MeddicheTruck.mtmain.services;

import com.MeddicheTruck.mtcore.handlingExceptions.costumExceptions.DoNotExistException;
import com.MeddicheTruck.mtcore.handlingExceptions.costumExceptions.ValidationException;
import com.MeddicheTruck.mtmain.dtos.VehicleTransactionIDto;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
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

        }

        @Test
        public void shouldThrowDoNotExistException_WhenVehicleNotInStock() {

        }

        @Test
        public void inStockShouldReturnFalse_WhenSellingTransactionHappens() {

        }

    }

    @Nested
    class BuyingTransactionTests {

        @Test
        public void shouldSaveVehicleTransaction_WhenVehicleNotInStock() {

        }

        @Test
        public void shouldThrowAlreadyExistsException_WhenVehicleInStock() {

        }

        @Test
        public void inStockShouldReturnTrue_WhenBuyingTransactionHappens() {

        }

    }

    /* globalValidationTests means validation that is not specific to a transaction type,
    * and validations that should be done before the save or update methods */
    @Nested
    class globalValidationTests {

        @Test
        public void shouldThrowDoNotExistException_WhenVehicleDoNotExist() {
            when(vehicleService.doesNotExistById(anyLong())).thenReturn(true);

            assertThrows(DoNotExistException.class, () -> service.save(VehicleTransactionIDto.builder()
                    .id(null)
                    .date(LocalDate.now())
                    .time(LocalTime.now())
                    .description("description")
                    .type("BUY")
                    .vehicleId(0L)
                    .personId(1L)
                    .price(100.0).build())
            );
        }

        @Test
        public void shouldThrowDoNotExistException_WhenPersonDoNotExist() {
            when(personService.doesNotExistById(anyLong())).thenReturn(true);

            assertThrows(DoNotExistException.class, () -> service.save(VehicleTransactionIDto.builder()
                    .id(null)
                    .date(LocalDate.now())
                    .time(LocalTime.now())
                    .description("description")
                    .type("BUY")
                    .vehicleId(1L)
                    .personId(0L)
                    .price(100.0).build())
            );
        }

    }

    @Nested
    class dtoValidationsForSavingASellingTransaction {

        @TestFactory
        public Stream<DynamicTest> shouldThrowValidationException_WhenDtoIsInvalidOnSaving() {
            return invalidDtoListForSaving().stream().map(dto -> DynamicTest.dynamicTest(dto.toString(),
                    () -> assertThrows(ConstraintViolationException.class, () -> service.save(dto)))
            );

        }

    }

    public List<VehicleTransactionIDto> invalidDtoListForSaving (){

        // Null values
        List<VehicleTransactionIDto> invalidDtoList =
                generateInvalidDtoList(this::createSampleDtoForSaving);

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

    public List<VehicleTransactionIDto> generateInvalidDtoList(Supplier<VehicleTransactionIDto> createSimpleDto) {

        List<VehicleTransactionIDto> invalidDtoList = new ArrayList<>();

        Field[] fields = VehicleTransactionIDto.class.getDeclaredFields();

        Arrays.stream(fields).forEach(field -> {

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
                .price(-100.0).build();
    }

}
