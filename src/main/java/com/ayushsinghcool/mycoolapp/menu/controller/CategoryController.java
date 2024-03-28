package com.ayushsinghcool.mycoolapp.menu.controller;

import com.ayushsinghcool.mycoolapp.menu.MenuManagerService;
import com.ayushsinghcool.mycoolapp.menu.model.MenuCategory;
import com.ayushsinghcool.mycoolapp.menu.validator.CategoryValidator;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private MenuManagerService menuManagerService;
    private CategoryValidator categoryValidator;
    public CategoryController(MenuManagerService menuManagerService, CategoryValidator categoryValidator) {
        this.menuManagerService = menuManagerService;
        this.categoryValidator = categoryValidator;
    }

    @PostMapping
    public ResponseEntity<String> createCategory(@Valid  @RequestBody MenuCategory menuCategory , BindingResult bindingResult){
        ResponseEntity<String> validateResponse = categoryValidator.validate(menuCategory,bindingResult);
        if(validateResponse.getStatusCode()!=HttpStatus.OK)
            return validateResponse;
        else{
            menuManagerService.addCategory(menuCategory);
            return new ResponseEntity<>("Category Added Successfully", HttpStatus.CREATED);
        }
    }

    @GetMapping()
    public ResponseEntity<List<MenuCategory>> fetchCategory(){
        return ResponseEntity.ok(menuManagerService.fetchCategory());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable int id){
        MenuCategory menuCategory = menuManagerService.fetchCategoryById(id);
        if(menuCategory != null)
            return new ResponseEntity<>(menuCategory, HttpStatus.OK) ;
        else return new ResponseEntity<>("Id does not present in the system",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable int id){
        if(menuManagerService.deleteCategory(id))
            return ResponseEntity.ok("Category Deleted Successfully");
        else
            return new ResponseEntity<>("Category does not exist in the system",HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable int id, @RequestBody MenuCategory menuCategory){
        if(menuManagerService.updateCategory(id,menuCategory)){
            return ResponseEntity.ok("Category updated Successfully");
        }
        else
            return new ResponseEntity<>(id+" Category ID doesn't present in the system.",HttpStatus.BAD_REQUEST);
    }
}
