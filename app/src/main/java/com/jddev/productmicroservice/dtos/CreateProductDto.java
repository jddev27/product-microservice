package com.jddev.productmicroservice.dtos;

public record CreateProductDto( String name, String description, double price, int quantity, String sku) {
}
