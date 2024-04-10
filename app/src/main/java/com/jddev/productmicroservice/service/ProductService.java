package com.jddev.productmicroservice.service;

import com.jddev.productmicroservice.dtos.CreateProductDto;
import com.jddev.productmicroservice.dtos.ProductDto;
import com.jddev.productmicroservice.dtos.updateProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();
    ProductDto getProductById(Long id);
    ProductDto createProduct(CreateProductDto productDto);
    ProductDto updateProduct(Long id, updateProductDto updateProductDto);
    void DeleteProduct(Long id);
}
