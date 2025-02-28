package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CarRepositoryTest {

    private CarRepository carRepository;

    @BeforeEach
    void setUp() {
        carRepository = new CarRepository();
    }

    @Test
    void testCreateCar() {
        Car car = new Car();
        car.setCarName("Honda Accord");
        car.setCarColor("Black");
        car.setCarQuantity(2);

        Car savedCar = carRepository.create(car);

        assertNotNull(savedCar.getCarId(), "Car ID should be generated");
        assertEquals("Honda Accord", savedCar.getCarName());
        assertEquals("Black", savedCar.getCarColor());
        assertEquals(2, savedCar.getCarQuantity());
    }

    @Test
    void testFindAllCars() {
        carRepository.create(new Car());
        carRepository.create(new Car());

        Iterator<Car> cars = carRepository.findAll();

        assertTrue(cars.hasNext(), "Car list should not be empty");
    }

    @Test
    void testFindCarById() {
        Car car = new Car();
        car.setCarId(UUID.randomUUID().toString());
        car.setCarName("Mazda MX-5");

        carRepository.create(car);

        Car retrievedCar = carRepository.findById(car.getCarId());

        assertNotNull(retrievedCar, "Car should be found");
        assertEquals("Mazda MX-5", retrievedCar.getCarName());
    }

    @Test
    void testFindCarById_NotFound() {
        Car retrievedCar = carRepository.findById("non-existent-id");
        assertNull(retrievedCar, "Should return null for non-existent ID");
    }

    @Test
    void testUpdateCar() {
        Car car = new Car();
        car.setCarId(UUID.randomUUID().toString());
        car.setCarName("Toyota Camry");
        car.setCarColor("White");
        car.setCarQuantity(3);

        carRepository.create(car);

        Car updatedCar = new Car();
        updatedCar.setCarName("Toyota Corolla");
        updatedCar.setCarColor("Red");
        updatedCar.setCarQuantity(5);

        Car result = carRepository.update(car.getCarId(), updatedCar);

        assertNotNull(result, "Updated car should not be null");
        assertEquals("Toyota Corolla", result.getCarName());
        assertEquals("Red", result.getCarColor());
        assertEquals(5, result.getCarQuantity());
    }

    @Test
    void testUpdateCar_NotFound() {
        Car updatedCar = new Car();
        updatedCar.setCarName("New Model");

        Car result = carRepository.update("non-existent-id", updatedCar);

        assertNull(result, "Update should return null for non-existent car ID");
    }

    @Test
    void testDeleteCar() {
        Car car = new Car();
        car.setCarId(UUID.randomUUID().toString());
        car.setCarName("Nissan Altima");

        carRepository.create(car);
        carRepository.delete(car.getCarId());

        assertNull(carRepository.findById(car.getCarId()), "Deleted car should not exist");
    }

    @Test
    void testDeleteCar_NotFound() {
        // Attempt to delete a non-existent car
        carRepository.delete("non-existent-id");
        assertTrue(true, "Deleting a non-existent car should not cause errors");
    }
}
