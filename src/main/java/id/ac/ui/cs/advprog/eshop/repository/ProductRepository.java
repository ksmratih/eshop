package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        if (product.getProductId() == null || product.getProductId().isEmpty()) {
            product.setProductId(UUID.randomUUID().toString()); // Generates UUID as String
        }
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product findById(String id) {
        if (id == null) {
            return null;
        }
        return productData.stream()
                .filter(product -> id.equals(product.getProductId()))
                .findFirst()
                .orElse(null);
    }

    public void update(String id, Product updatedProduct) {
        Product product = findById(id);
        if (product != null) {
            product.setProductName(updatedProduct.getProductName());
            product.setProductQuantity(updatedProduct.getProductQuantity());
        }
    }

}