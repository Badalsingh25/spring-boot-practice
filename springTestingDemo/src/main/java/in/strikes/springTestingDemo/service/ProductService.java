package in.strikes.springTestingDemo.service;

import in.strikes.springTestingDemo.entity.Product;
import in.strikes.springTestingDemo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    
    private final ProductRepository productRepository;
    
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not exist with id :" + id));
    }
    
    public Product createProduct(Product product) {
        boolean alreadyExists = productRepository.existsByName(product.getName());
        if (alreadyExists) {
            throw new IllegalArgumentException("Product already exists with name : " + product.getName());
        }
        return productRepository.save(product);
    }
    
    
}
