package com.example.Shopito.Controllers;

import com.example.Shopito.Dtos.CategoryRequestDto;
import com.example.Shopito.Dtos.CategoryResponseDto;
import com.example.Shopito.Entities.Category;
import com.example.Shopito.Services.CategoryService;
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


    @DeleteMapping("/categories/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> DeleteCategoryById(@PathVariable int id){
         categoryService.DeleteCategoryById(id);
         return ResponseEntity.ok("Category deleted successfully.");
    }





}
