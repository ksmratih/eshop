package id.ac.ui.cs.advprog.eshop.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import id.ac.ui.cs.advprog.eshop.controller.ProductController;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

class ProductControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private Model model;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProductPage() {
        String viewName = productController.createProductPage(model);
        assertEquals("createProduct", viewName);
        verify(model).addAttribute(eq("product"), any(Product.class));
    }

    @Test
    void testCreateProductPost() {
        Product product = new Product();
        String viewName = productController.createProductPost(product);
        assertEquals("redirect:/product/list", viewName);
        verify(productService).create(product);
    }

    @Test
    void testProductListPage() {
        List<Product> products = Arrays.asList(new Product(), new Product());
        when(productService.findAll()).thenReturn(products);
        String viewName = productController.productListPage(model);
        assertEquals("productList", viewName);
        verify(model).addAttribute("products", products);
    }

    @Test
    void testEditProductPage_Found() {
        Product product = new Product();
        when(productService.findById("1")).thenReturn(product);
        String viewName = productController.editProductPage("1", model);
        assertEquals("editProduct", viewName);
        verify(model).addAttribute("product", product);
    }

    @Test
    void testEditProductPage_NotFound() {
        when(productService.findById("1")).thenReturn(null);
        String viewName = productController.editProductPage("1", model);
        assertEquals("redirect:/product/list", viewName);
    }

    @Test
    void testEditProduct() {
        Product product = new Product();
        String viewName = productController.editProduct("1", product);
        assertEquals("redirect:/product/list", viewName);
        verify(productService).update("1", product);
    }

    @Test
    void testDeleteProduct() {
        String viewName = productController.deleteProduct("1");
        assertEquals("redirect:/product/list", viewName);
        verify(productService).delete("1");
    }
}
