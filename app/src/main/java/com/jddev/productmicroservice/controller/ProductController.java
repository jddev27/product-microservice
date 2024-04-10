package com.jddev.productmicroservice.controller;

import com.jddev.productmicroservice.dtos.CreateProductDto;
import com.jddev.productmicroservice.dtos.ProductDto;
import com.jddev.productmicroservice.dtos.updateProductDto;
import com.jddev.productmicroservice.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<ProductDto> CreateProduct(@RequestBody CreateProductDto createProductDto){

        ProductDto response = productService.createProduct(createProductDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody updateProductDto updateProductDto, @PathVariable Long id){
            ProductDto response = productService.updateProduct(id, updateProductDto);
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        productService.DeleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
