package com.example.Shopito.Controllers;

import com.example.Shopito.Dtos.Category.CategoryRequestDto;
import com.example.Shopito.Dtos.Category.CategoryResponseDto;
import com.example.Shopito.Entities.Category;
import com.example.Shopito.Services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {


   @Autowired
   private CategoryService categoryService;



    @Operation(
            summary = "Add New Category (Admin Only)",
            description = """
        Creates a new product category in the system.  

        🛡️ **Admin privileges are required.**  
        📥 Requires category name and optional description.  
        🚫 If a category with the same name already exists, a conflict error may occur.
    """
    )

    @PostMapping("/admin/categories")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?>AddCategory( @Valid  @RequestBody CategoryRequestDto dto){
        Category category = new Category();
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());


        Category saved = categoryService.save(category);


        CategoryResponseDto response = new CategoryResponseDto();
        response.setId(saved.getId());
        response.setName(saved.getName());
        response.setDescription(saved.getDescription());


        return ResponseEntity.ok(response);

    }

    @Operation(
            summary = "Get All Categories",
            description = """
        Retrieves a complete list of all product categories available in the system.

        📦 Useful for populating dropdowns, filters, or category navigation in the UI.
        🔓 Publicly accessible – no authentication required.
    """
    )

    @GetMapping("/categories")
    public ResponseEntity<List<?>>GetAllCategories(){

        List<Category>result = categoryService.GetAll();
        List<CategoryResponseDto>response = new ArrayList<>();

        for(Category category : result){
            CategoryResponseDto dto = new CategoryResponseDto();
            dto.setId(category.getId());
            dto.setName(category.getName());
            dto.setDescription(category.getDescription());
            response.add(dto);

        }
        return ResponseEntity.ok(response);

    }

    @Operation(
            summary = "Delete Category by ID (Admin Only)",
            description = """
        Deletes a specific category from the system using its unique ID.

        🛡️ **Requires admin privileges**  
        🗑️ Cannot delete a category if it is associated with existing products.  
        🔐 Authentication and authorization are required.
    """
    )

    @DeleteMapping("/categories/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> DeleteCategoryById(@PathVariable int id){
         categoryService.DeleteCategoryById(id);
         return ResponseEntity.ok("Category deleted successfully.");
    }





}
