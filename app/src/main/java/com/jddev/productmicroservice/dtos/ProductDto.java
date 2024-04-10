package com.jddev.productmicroservice.dtos;

public record ProductDto(Long id, String name, String description, double price, int quantity, String sku)  {
}
