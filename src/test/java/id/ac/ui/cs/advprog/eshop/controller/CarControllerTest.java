package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CarController.class)
@ExtendWith(MockitoExtension.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarServiceImpl carServiceMock;

    @BeforeEach
    void initTestSetup(@Autowired WebApplicationContext appContext) {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(appContext).build();
    }

    @Test
    void verifyCarListRetrieval() throws Exception {
        when(carServiceMock.findAll()).thenReturn(List.of());

        mockMvc.perform(get("/car/listCar"))
                .andExpect(status().isOk())
                .andExpect(view().name("carList"))
                .andExpect(model().attributeExists("cars"));

        verify(carServiceMock, times(1)).findAll();
    }

    @Test
    void verifyCarCreationPageLoads() throws Exception {
        mockMvc.perform(get("/car/createCar"))
                .andExpect(status().isOk())
                .andExpect(view().name("createCar"))
                .andExpect(model().attributeExists("car"));
    }

    @Test
    void validateCarCreationProcess() throws Exception {
        Car testCar = new Car();
        testCar.setCarName("Nissan Skyline");
        testCar.setCarColor("Blue");
        testCar.setCarQuantity(2);

        when(carServiceMock.create(any(Car.class))).thenReturn(testCar);

        mockMvc.perform(post("/car/createCar")
                        .flashAttr("car", testCar))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("listCar"));

        verify(carServiceMock, times(1)).create(any(Car.class));
    }

    @Test
    void confirmCarEditPageLoads() throws Exception {
        Car existingCar = new Car();
        existingCar.setCarId("ABC-456");
        existingCar.setCarName("Subaru WRX");

        when(carServiceMock.findById("ABC-456")).thenReturn(existingCar);

        mockMvc.perform(get("/car/editCar/ABC-456"))
                .andExpect(status().isOk())
                .andExpect(view().name("editCar"))
                .andExpect(model().attributeExists("car"));

        verify(carServiceMock, times(1)).findById("ABC-456");
    }

    @Test
    void validateCarEditProcess() throws Exception {
        Car updatedCar = new Car();
        updatedCar.setCarId("XYZ-789");
        updatedCar.setCarName("Mazda RX-8");

        mockMvc.perform(post("/car/editCar")
                        .flashAttr("car", updatedCar))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("listCar"));

        verify(carServiceMock, times(1)).update(eq("XYZ-789"), any(Car.class));
    }

    @Test
    void confirmCarDeletionWorks() throws Exception {
        mockMvc.perform(post("/car/deleteCar")
                        .param("carId", "ABC-456"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("listCar"));

        verify(carServiceMock, times(1)).deleteCarById("ABC-456");
    }
}
