package com.ayushsinghcool.mycoolapp.menu.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    @NotEmpty(message = "Name cannot be blank or null")
    private String name;

    private String description;

    @Embedded
    private Rate rate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private MenuCategory category;

    public MenuItem() {
    }

}
