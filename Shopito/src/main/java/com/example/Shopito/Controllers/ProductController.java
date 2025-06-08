package com.example.Shopito.Controllers;


import com.example.Shopito.Dtos.ProductRequestDto;
import com.example.Shopito.Entities.Product;
import com.example.Shopito.Services.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
//@RequestMapping("/admin/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @Operation(
            summary = "Add a new product",
            description = "This endpoint used to add product to the stock by an Admin."
    )
    @PostMapping("/admin/products")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?>createProduct(@RequestPart (value = "product") @Valid ProductRequestDto dto, @RequestPart(value="image",required = false) MultipartFile image) throws IOException {
        return  new ResponseEntity<>(service.createProduct(dto,image),HttpStatus.CREATED);

    }

    @Operation(
            summary = "Update product",
            description = "This endpoint used to update product by an Admin."
    )
    @PutMapping("/admin/products/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?>UpdateProduct( @PathVariable int id,@RequestBody @Valid ProductRequestDto dto){
        boolean updated = service.update(id, dto);

        if (!updated) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Product updated successfully");


    }


    @Operation(
            summary = "GetAll products",
            description = "This endpoint used to to get all products."
    )
    @GetMapping("/products")

    public ResponseEntity<List<ProductRequestDto>>GetAllProducts(){
        return ResponseEntity.ok(service.GetAllProducts());
    }

    @Operation(
            summary = "Delete product by id",
            description = "This endpoint used to delete product by id."
    )
    @DeleteMapping("/admin/products/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?>DeleteProduct(@PathVariable int id){

        boolean deleted = service.DeleteProductById(id);
        if(deleted)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.notFound().build();

    }


    @Operation(
            summary = "Get product by id",
            description = "This endpoint used to Get product by id."
    )
    @GetMapping("/products/{id}")
    public ResponseEntity<?>GetProductById(@PathVariable  int id){
         return ResponseEntity.ok(service.GetProductById(id));
    }


    @Operation(
            summary = "Get All products",
            description = "This endpoint used to Get all products that belongs to specific category."
    )

    @GetMapping("/products/category/{name}")
    public ResponseEntity<List<?>>GetProductByCategory(@PathVariable String name){
        return ResponseEntity.ok(service.GetProductsByCategoryName(name));
    }











}
