package com.ayushsinghcool.mycoolapp.menu.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NonNull;

@Data
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "category")})
public class MenuCategory {

    public MenuCategory(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    @NotEmpty(message = "Category cannot be blank or null")
    private String category;

    public MenuCategory(@NonNull String category) {
        this.category = category;
    }
}
