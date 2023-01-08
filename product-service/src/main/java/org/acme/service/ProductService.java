package org.acme.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import org.acme.dto.ProductForm;
import org.acme.dto.ProductResponse;
import org.acme.entity.Product;
import org.acme.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductResponse> all() {
        return productRepository.findAll().stream()
                .map(this::from)
                .collect(Collectors.toList());

    }

    public ProductResponse findById(Long id) {
        var product = productRepository.findById(id);
        if (product == null) {
            throw new RuntimeException("Product not found");
        }
        return from(product);
    }

    @Transactional
    public void create(ProductForm form) {
        productRepository.persist(to(form));
    }

    @Transactional
    public void update(Long id, ProductForm form) {
        var product = productRepository.findById(id);
        if (product == null) {
            throw new RuntimeException("Product not found");
        }
        product.setName(form.getName());
        product.setDescription(form.getDescription());
        product.setPrice(form.getPrice());
    }

    @Transactional
    public void delete(Long id) {
        var product = productRepository.findById(id);
        if (product == null) {
            throw new RuntimeException("Product not found");
        }
        productRepository.delete(product);
    }

    private ProductResponse from(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    private Product to(ProductForm form) {
        return Product.builder()
                .name(form.getName())
                .description(form.getDescription())
                .price(form.getPrice())
                .build();
    }
}
