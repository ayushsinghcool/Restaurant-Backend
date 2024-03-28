package com.ayushsinghcool.mycoolapp.menu.validator;

import com.ayushsinghcool.mycoolapp.menu.model.MenuCategory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;


@Component
public class CategoryValidator {
    public ResponseEntity<String> validate(MenuCategory menuCategory, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            for (ObjectError error : bindingResult.getAllErrors()) {
                errorMessage.append(error.getDefaultMessage()).append("\n");
            }
            return ResponseEntity.badRequest().body(errorMessage.toString());
        }
        return ResponseEntity.ok().build();
    }
}
