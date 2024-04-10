package com.jddev.productmicroservice.service;

import com.jddev.productmicroservice.dtos.CreateProductDto;
import com.jddev.productmicroservice.dtos.ProductDto;
import com.jddev.productmicroservice.dtos.updateProductDto;
import com.jddev.productmicroservice.model.Product;
import com.jddev.productmicroservice.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(Long id) {
        Product foundProduct = getProduct(id);

        return mapToDto(foundProduct);
    }


    @Override
    public ProductDto createProduct(CreateProductDto productDto) {

        Product newProduct = mapToEntity(productDto);

        Product saveProduct = productRepository.save(newProduct);
        return mapToDto(saveProduct);
    }

    @Override
    public ProductDto updateProduct(Long id, updateProductDto updateProductDto) {
        Product existingProduct = getProduct(id);

        existingProduct.setName(updateProductDto.name());
        existingProduct.setDescription(updateProductDto.description());
        existingProduct.setPrice(updateProductDto.price());
        existingProduct.setQuantity(updateProductDto.quantity());

        Product updatedProduct = productRepository.save(existingProduct);
        return mapToDto(updatedProduct);
    }

    @Override
    public void DeleteProduct(Long id) {
        Product existingProduct = getProduct(id);

        productRepository.deleteById(existingProduct.getId());
    }

    private Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    private Product mapToEntity(CreateProductDto createProductDto) {
        Product product = new Product();
        product.setName(createProductDto.name());
        product.setDescription(createProductDto.description());
        product.setPrice(createProductDto.price());
        product.setQuantity(createProductDto.quantity());
        product.setSku(createProductDto.sku());

        return product;
    }

    private ProductDto mapToDto(Product product) {

        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                product.getSku());
    }


}
