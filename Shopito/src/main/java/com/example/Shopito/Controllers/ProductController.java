package com.example.Shopito.Controllers;


import com.example.Shopito.Dtos.product.ProductRequestDto;
import com.example.Shopito.Entities.Product;
import com.example.Shopito.Services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
            summary = "Add a New Product",
            description = """
        This endpoint allows an **admin** to add a new product to the store's inventory.

        📦 Requires product details such as name, description, price, quantity, image, category, etc.  
        🔐 Only users with **ADMIN role** are authorized to access this endpoint.
        
        The newly added product will become available for users to view and purchase.
    """
    )

    @PostMapping("/admin/products")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?>createProduct(@RequestPart (value = "product") @Valid ProductRequestDto dto, @RequestPart(value="image",required = false) MultipartFile image) throws IOException {
        return  new ResponseEntity<>(service.createProduct(dto,image),HttpStatus.CREATED);

    }

    @Operation(
            summary = "Update an Existing Product",
            description = """
        This endpoint allows an **admin** to update the details of an existing product in the inventory.

        🔄 You can update fields such as name, description, price, quantity, image, category, and more.  
        🔐 Requires authentication and **ADMIN role** authorization.

        This ensures that the product information remains accurate and up-to-date.
    """
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
            summary = "Get All Products",
            description = """
        This endpoint retrieves a list of all available products in the store.

        🛍️ Useful for displaying products on the homepage or product listing page.  
        📦 Each product includes information like name, price, description, image, and category.

        This endpoint is **publicly accessible** (no authentication required).
    """
    )

    @GetMapping("/products")

    public ResponseEntity<List<ProductRequestDto>>GetAllProducts(){
        return ResponseEntity.ok(service.GetAllProducts());
    }

    @Operation(
            summary = "Delete Product by ID",
            description = """
        This endpoint allows an **admin** to delete a product from the inventory using its ID.

        🗑️ Once deleted, the product will no longer appear in the product listings.  
        🔐 Requires authentication and **ADMIN role** authorization.

        Make sure the product ID exists before attempting deletion.
    """
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
            summary = "Get Product by ID",
            description = """
        This endpoint retrieves the details of a specific product using its unique ID.

        🔍 Useful for viewing detailed information about a product, such as its name, description, price, category, and image.

        📦 The product must exist in the database; otherwise, a **404 Not Found** response will be returned.
    """
    )

    @GetMapping("/products/{id}")
    public ResponseEntity<?>GetProductById(@PathVariable  int id){
         return ResponseEntity.ok(service.GetProductById(id));
    }


    @Operation(
            summary = "Get All Products by Category",
            description = """
        This endpoint retrieves all products that belong to a specific category.

        📂 You must provide the category ID or name as a path or query parameter, depending on implementation.  
        🛍️ Useful for filtering products on category pages.

        Returns a list of matching products, each including details like name, price, description, and image.
    """
    )


    @GetMapping("/products/category/{name}")
    public ResponseEntity<List<?>>GetProductByCategory(@PathVariable String name){
        return ResponseEntity.ok(service.GetProductsByCategoryName(name));
    }


    @Operation(
            summary = "Search products",
            description = """
        Search for products by keyword in name or description.

        🔍 The keyword is matched case-insensitively.  
        🧾 Example: `/products/search?keyword=laptop`  
        📦 Returns a list of products containing the keyword in either the name or the description.
    """
    )
    @GetMapping("/search")
    public ResponseEntity<List<?>>searchProducts(String keyword){
        List<Product> products = service.searchByKeyword(keyword);
        return ResponseEntity.ok(products);
    }











}
