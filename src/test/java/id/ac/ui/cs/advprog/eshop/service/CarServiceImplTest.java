package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CarServiceImplTest {

    @Mock
    private CarRepository carRepository; // Mock the repository

    @InjectMocks
    private CarServiceImpl carService; // Inject mocks into service

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCar() {
        Car car = new Car();
        car.setCarName("Honda Civic");
        car.setCarColor("Black");
        car.setCarQuantity(4);

        when(carRepository.create(car)).thenReturn(car);

        Car savedCar = carService.create(car);

        assertNotNull(savedCar, "Created car should not be null");
        assertEquals("Honda Civic", savedCar.getCarName(), "Car name should match");
        assertEquals("Black", savedCar.getCarColor(), "Car color should match");
        assertEquals(4, savedCar.getCarQuantity(), "Car quantity should match");
        verify(carRepository, times(1)).create(car);
    }

    @Test
    void testFindAllCars() {
        Car car1 = new Car();
        Car car2 = new Car();
        List<Car> mockCars = Arrays.asList(car1, car2);
        Iterator<Car> mockIterator = mockCars.iterator();

        when(carRepository.findAll()).thenReturn(mockIterator);

        List<Car> result = carService.findAll();

        assertEquals(2, result.size(), "Should return 2 cars");
        verify(carRepository, times(1)).findAll();
    }

    @Test
    void testFindCarById() {
        Car car = new Car();
        String carId = UUID.randomUUID().toString();
        car.setCarId(carId);
        car.setCarName("Mazda RX-7");

        when(carRepository.findById(carId)).thenReturn(car);

        Car result = carService.findById(carId);

        assertNotNull(result, "Car should be found");
        assertEquals("Mazda RX-7", result.getCarName(), "Car name should match");
        verify(carRepository, times(1)).findById(carId);
    }

    @Test
    void testUpdateCar() {
        Car car = new Car();
        String carId = UUID.randomUUID().toString();
        car.setCarId(carId);
        car.setCarName("Toyota Supra");
        car.setCarColor("Red");
        car.setCarQuantity(2);

        Car updatedCar = new Car();
        updatedCar.setCarName("Toyota Corolla");
        updatedCar.setCarColor("Blue");
        updatedCar.setCarQuantity(5);

        when(carRepository.update(carId, updatedCar)).thenReturn(updatedCar);

        carService.update(carId, updatedCar);

        verify(carRepository, times(1)).update(carId, updatedCar);
    }

    @Test
    void testDeleteCar() {
        String carId = UUID.randomUUID().toString();

        doNothing().when(carRepository).delete(carId);

        carService.deleteCarById(carId);

        verify(carRepository, times(1)).delete(carId);
    }
}
