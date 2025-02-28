package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    @Test
    void testCarObjectCreation() {
        Car car = new Car();
        assertNotNull(car, "Car object should be created successfully");
    }

    @Test
    void testCarSettersAndGetters() {
        Car car = new Car();
        car.setCarId("123ABC");
        car.setCarName("Honda Civic");
        car.setCarColor("Blue");
        car.setCarQuantity(5);

        assertEquals("123ABC", car.getCarId(), "Car ID should match the expected value");
        assertEquals("Honda Civic", car.getCarName(), "Car name should match the expected value");
        assertEquals("Blue", car.getCarColor(), "Car color should match the expected value");
        assertEquals(5, car.getCarQuantity(), "Car quantity should match the expected value");
    }

    @Test
    void testDefaultValues() {
        Car car = new Car();
        assertNull(car.getCarId(), "Car ID should be null by default");
        assertNull(car.getCarName(), "Car name should be null by default");
        assertNull(car.getCarColor(), "Car color should be null by default");
        assertEquals(0, car.getCarQuantity(), "Car quantity should default to 0");
    }

    @Test
    void testCarModification() {
        Car car = new Car();
        car.setCarId("ABC123");
        car.setCarName("Toyota Supra");
        car.setCarColor("Red");
        car.setCarQuantity(3);

        car.setCarName("Toyota Corolla");
        car.setCarColor("White");
        car.setCarQuantity(10);

        assertEquals("Toyota Corolla", car.getCarName(), "Car name should update correctly");
        assertEquals("White", car.getCarColor(), "Car color should update correctly");
        assertEquals(10, car.getCarQuantity(), "Car quantity should update correctly");
    }
}
