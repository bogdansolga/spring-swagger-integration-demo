package com.sg.swagger.demo.service;

import com.sg.swagger.demo.dto.ProductDTO;
import com.sg.swagger.demo.model.Product;
import com.sg.swagger.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void create(final ProductDTO productDTO) {
        productRepository.save(productDTOConverter().apply(productDTO));
    }

    public ProductDTO get(final int id) {
        final Product product = Optional.ofNullable(productRepository.findOne(id))
                                        .orElseThrow(() -> new IllegalArgumentException("There is no product with the id " + id));

        return productConverter().apply(product);
    }

    public List<ProductDTO> getAll() {
        return StreamSupport.stream(productRepository.findAll().spliterator(), false)
                            .map(productConverter())
                            .collect(Collectors.toList());
    }

    public void update(final int id, final ProductDTO productDTO) {
        final Product existingProduct = productRepository.findOne(id);

        existingProduct.setName(productDTO.getProductName());

        productRepository.save(existingProduct);
    }

    public void delete(final int id) {
        productRepository.delete(id);
    }

    private Function<ProductDTO, Product> productDTOConverter() {
        return dto -> new Product(dto.getId(), dto.getProductName(), dto.getPrice(), null);
    }

    private Function<Product, ProductDTO> productConverter() {
        return product -> new ProductDTO(product.getId(), product.getName(), product.getPrice());
    }
}
