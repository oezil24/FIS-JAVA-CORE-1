package fis.com.test_final.service;

import fis.com.test_final.model.Product;

public interface ProductService {
    Product findById(Long productId);
    Product update(Product product);
}
