package com.ayushsinghcool.mycoolapp.menu.validator;

import com.ayushsinghcool.mycoolapp.menu.model.MenuItem;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.Objects;

@Component
public class MenuValidator {

    public ResponseEntity<String> validate(List<MenuItem> menuItems, BindingResult bindingResult) {

        if (menuItems.stream().allMatch(Objects::isNull)) {
            return ResponseEntity.badRequest().body("Request body cannot be empty. At least one menu item is required.");
        }

       else if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            for (ObjectError error : bindingResult.getAllErrors()) {
                errorMessage.append(error.getDefaultMessage()).append("\n");
            }
            return ResponseEntity.badRequest().body(errorMessage.toString());
        }

        else if(menuItems.stream().anyMatch(menuItem ->
                menuItem.isSpecial() || !menuItem.isStatus())) {
            return ResponseEntity.badRequest().body("Boolean fields are not allowed in the request body");
        }

        else if(menuItems.stream().anyMatch(menuItem ->
                menuItem.getRate()==null ||
                menuItem.getRate().getHalf()==null ||
                menuItem.getRate().getFull()==null ||
                menuItem.getRate().getHalf().equals(0.0)||
                        menuItem.getRate().getFull().equals(0.0))){
            return ResponseEntity.badRequest().body("Rate can't be null or zero");
        }

        else if(menuItems.stream().anyMatch(menuItem ->
                    menuItem.getCategories() == null ||
                menuItem.getCategories().getCategory().isEmpty())){
            return ResponseEntity.badRequest().body("Category can't be null or empty");
        }

        return ResponseEntity.ok().build();
    }

}
