package id.ac.ui.cs.advprog.eshop.controller;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import id.ac.ui.cs.advprog.eshop.controller.HomeController;

class HomeControllerTest {

    @Test
    void testHomePage() {
        HomeController homeController = new HomeController();
        String viewName = homeController.homePage();

        assertEquals("Home", viewName); // Ensure the correct view is returned
    }
}
