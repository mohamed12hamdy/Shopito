package com.example.Shopito.Services;

import com.example.Shopito.Entities.Category;
import com.example.Shopito.Repositories.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public Category save(Category category){
        return categoryRepository.save(category);
    }
    public List<Category>GetAll(){
        return categoryRepository.findAll();
    }

    @Transactional
    public  String DeleteCategoryById(int id){
        if (!categoryRepository.existsById(id)) {
            return "Category with ID " + id + " not found.";
        }

        categoryRepository.deleteById(id);
        return "Category deleted successfully.";
    }


}
