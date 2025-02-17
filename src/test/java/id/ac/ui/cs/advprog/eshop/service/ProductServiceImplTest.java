package id.ac.ui.cs.advprog.eshop.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import id.ac.ui.cs.advprog.eshop.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product sampleProduct;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleProduct = new Product();
        sampleProduct.setProductId("1");
        sampleProduct.setProductName("Test Product");
        sampleProduct.setProductQuantity(10);
    }

    @Test
    void testCreateProduct() {
        when(productRepository.create(any(Product.class))).thenReturn(sampleProduct);

        Product result = productService.create(sampleProduct);

        assertNotNull(result);
        assertEquals("Test Product", result.getProductName());
        verify(productRepository).create(sampleProduct);
    }

    @Test
    void testFindAll() {
        List<Product> mockProducts = Arrays.asList(sampleProduct, new Product());
        Iterator<Product> productIterator = mockProducts.iterator();

        when(productRepository.findAll()).thenReturn(productIterator);

        List<Product> products = productService.findAll();

        assertEquals(2, products.size());
        verify(productRepository).findAll();
    }

    @Test
    void testFindById_Exists() {
        when(productRepository.findById("1")).thenReturn(sampleProduct);

        Product result = productService.findById("1");

        assertNotNull(result);
        assertEquals("Test Product", result.getProductName());
        verify(productRepository).findById("1");
    }

    @Test
    void testFindById_NotExists() {
        when(productRepository.findById("2")).thenReturn(null);

        Product result = productService.findById("2");

        assertNull(result);
        verify(productRepository).findById("2");
    }

    @Test
    void testUpdateProduct() {
        doNothing().when(productRepository).update(eq("1"), any(Product.class));

        productService.update("1", sampleProduct);

        verify(productRepository).update("1", sampleProduct);
    }

    @Test
    void testDeleteProduct() {
        doNothing().when(productRepository).delete("1");

        productService.delete("1");

        verify(productRepository).delete("1");
    }
}
