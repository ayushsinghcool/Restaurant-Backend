package com.ayushsinghcool.mycoolapp.menu.controller;

import com.ayushsinghcool.mycoolapp.menu.model.MenuItem;
import com.ayushsinghcool.mycoolapp.menu.MenuManagerService;
import com.ayushsinghcool.mycoolapp.menu.validator.MenuValidator;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuItemController {
    private MenuManagerService menuManagerService;
    private MenuValidator menuValidator;
    public MenuItemController(MenuManagerService menuManagerService, MenuValidator menuValidator) {
        this.menuManagerService = menuManagerService;
        this.menuValidator = menuValidator;
    }

    @GetMapping
    public ResponseEntity<List<MenuItem>> findAll(){
        return ResponseEntity.ok(menuManagerService.fetchMenu());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMenuById(@PathVariable int id){
        MenuItem menuItem = menuManagerService.fetchMenuById(id);
        if(menuItem != null)
            return new ResponseEntity<>(menuItem, HttpStatus.OK) ;
        else return new ResponseEntity<>("Id does not present in the system",HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> createMenu(@Valid @RequestBody List<MenuItem> menuItem, BindingResult bindingResult){
        ResponseEntity<String> validationResponse = menuValidator.validate(menuItem, bindingResult);

        if (validationResponse.getStatusCode() != HttpStatus.OK) {
            return validationResponse;
        }
        else {
            boolean saved = menuManagerService.createMenu(menuItem);
            if (saved)
                return ResponseEntity.ok("Menu item created successfully");
            else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create menu item. Invalid category.");
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMenu(@PathVariable int id){
        if(menuManagerService.deleteMenu(id))
            return ResponseEntity.ok("Item Deleted Successfully");
        else
            return new ResponseEntity<>("Item does not exist in the system",HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateMenu(@PathVariable int id, @RequestBody MenuItem menuItem){
        if(menuManagerService.updateMenu(id,menuItem)){
            return ResponseEntity.ok("Item updated Successfully");
        }
        else
            return new ResponseEntity<>("Failed to create menu item. Invalid category.",HttpStatus.BAD_REQUEST);
    }

}
