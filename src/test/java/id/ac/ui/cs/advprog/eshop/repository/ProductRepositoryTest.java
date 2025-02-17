package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateProduct_WithNullId() {
        Product product = new Product();
        product.setProductId(null);
        Product savedProduct = productRepository.create(product);
        assertNotNull(savedProduct.getProductId());
        assertFalse(savedProduct.getProductId().isEmpty());
    }

    @Test
    void testCreateProduct_WithEmptyId() {
        Product product = new Product();
        product.setProductId("");
        Product savedProduct = productRepository.create(product);
        assertNotNull(savedProduct.getProductId());
        assertFalse(savedProduct.getProductId().isEmpty());
    }

    @Test
    void testFindById_NullId() {
        Product result = productRepository.findById(null);
        assertNull(result);
    }

    @Test
    void testFindById_ValidId() {
        Product product = new Product();
        product.setProductId("valid-id");
        productRepository.create(product);
        Product foundProduct = productRepository.findById("valid-id");
        assertNotNull(foundProduct);
        assertEquals("valid-id", foundProduct.getProductId());
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testUpdateProduct() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        updatedProduct.setProductName("Sampo Cap Bambang Baru");
        updatedProduct.setProductQuantity(200);
        productRepository.update(updatedProduct.getProductId(), updatedProduct);

        Product result = productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertNotNull(result);
        assertEquals("Sampo Cap Bambang Baru", result.getProductName());
        assertEquals(200, result.getProductQuantity());
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        productRepository.create(product);

        assertNotNull(productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6"));
        productRepository.delete("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertNull(productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6"));
    }
}
