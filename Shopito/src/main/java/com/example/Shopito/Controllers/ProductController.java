package com.example.Shopito.Controllers;


import com.example.Shopito.Dtos.ProductRequestDto;
import com.example.Shopito.Entities.Product;
import com.example.Shopito.Services.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/admin/products")
public class ProductController {

    @Autowired
    private ProductService service;


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?>createProduct(@RequestPart (value = "product") @Valid ProductRequestDto dto, @RequestPart(value="image",required = false) MultipartFile image) throws IOException {
        return  new ResponseEntity<>(service.createProduct(dto,image),HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?>UpdateProduct( @PathVariable int id,@RequestBody @Valid ProductRequestDto dto){
        boolean updated = service.update(id, dto);

        if (!updated) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Product updated successfully");


    }





}
