package fis.com.test_final.service.impl;

import fis.com.test_final.exception.ProductNotFoundException;
import fis.com.test_final.model.Product;
import fis.com.test_final.repository.ProductRepo;
import fis.com.test_final.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepo;

    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public Product findById(Long productId) {
        Product product = productRepo.findById(productId).orElseThrow(() -> {
            try {
                throw new ProductNotFoundException(String.format("Not found product with id %s", productId));
            } catch (ProductNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        log.info("Product with id {} : {}", productId, product);
        return product;
    }

    @Override
    public Product update(Product product) {
        return productRepo.save(product);
    }
}
