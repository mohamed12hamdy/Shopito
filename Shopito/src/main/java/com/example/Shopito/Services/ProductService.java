package com.example.Shopito.Services;

import com.example.Shopito.Dtos.ProductRequestDto;
import com.example.Shopito.Entities.Category;
import com.example.Shopito.Entities.Product;
import com.example.Shopito.Exceptions.ProductAlreadyExist;
import com.example.Shopito.Repositories.CategoryRepository;
import com.example.Shopito.Repositories.productRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    private static final String UPLOAD_DIRECTORY = "src/main/resources/static/images/";
    @Autowired
    private productRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;
    public ProductRequestDto createProduct(ProductRequestDto dto,MultipartFile imageFile) throws IOException {
        if (repository.existsByName(dto.getName())) {
            throw new ProductAlreadyExist("Product with this name already exists!");
        }

        Product product = convertToEntity(dto);
        if(imageFile!=null && !imageFile.isEmpty()){
            String fileName=saveImage(imageFile);
            product.setImage("/images/"+fileName);
        }
        Product saved = repository.save(product);
        return convertToDTO(saved);
    }

    private String saveImage(MultipartFile  imageFile) throws IOException {
        String fileName= UUID.randomUUID().toString()+"_"+imageFile.getOriginalFilename();
        Path path= Paths.get(UPLOAD_DIRECTORY+fileName);
        Files.createDirectories(path.getParent());
        Files.write(path, imageFile.getBytes());
        return fileName;

    }

    private Product convertToEntity(ProductRequestDto dto){
        Product product = new Product();

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());
        product.setImage(dto.getImage());

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + dto.getCategoryId()));
        product.setCategory(category);

        return product;

    }


    private ProductRequestDto convertToDTO(Product product){
         ProductRequestDto dto = new ProductRequestDto();

        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setQuantity(product.getQuantity());
        dto.setImage(product.getImage());
        dto.setCategoryId(product.getCategory().getId());

        return dto;
    }


    public boolean update(int id, ProductRequestDto dto){
        Product optionalProduct = repository.findById(id).orElse(null);
        if(optionalProduct != null){

            optionalProduct.setName(dto.getName());
            optionalProduct.setDescription(dto.getDescription());
            optionalProduct.setPrice(dto.getPrice());
            optionalProduct.setImage(dto.getImage());
            optionalProduct.setQuantity(dto.getQuantity());

            repository.save(optionalProduct);
            return true;
        }
        else{
            return false;
        }



    }


}
