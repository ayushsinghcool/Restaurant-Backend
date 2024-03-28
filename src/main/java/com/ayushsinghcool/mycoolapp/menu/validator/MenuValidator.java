package com.ayushsinghcool.mycoolapp.menu.validator;

import com.ayushsinghcool.mycoolapp.menu.model.MenuItem;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

@Component
public class MenuValidator {

    public ResponseEntity<String> validate(List<MenuItem> menuItems, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            for (ObjectError error : bindingResult.getAllErrors()) {
                errorMessage.append(error.getDefaultMessage()).append("\n");
            }
            return ResponseEntity.badRequest().body(errorMessage.toString());
        }

        else if(menuItems.stream().anyMatch(menuItem ->
                menuItem.getRate().getHalf()==null ||
                menuItem.getRate().getFull()==null ||
                menuItem.getRate().getHalf().equals(0.0)||
                        menuItem.getRate().getFull().equals(0.0))){
            return ResponseEntity.badRequest().body("Rate can't be null or zero");
        }

        else if(menuItems.stream().anyMatch(menuItem ->
                    menuItem.getCategory() == null ||
                menuItem.getCategory().getCategory().isEmpty())){
            return ResponseEntity.badRequest().body("Category can't be null or empty");
        }

        return ResponseEntity.ok().build();
    }
}
