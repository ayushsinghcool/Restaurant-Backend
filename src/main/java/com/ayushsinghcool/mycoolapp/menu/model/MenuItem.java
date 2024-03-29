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
    @NotEmpty(message = "Name cannot be null or empty")
    private String name;

    private String description;

    @NotEmpty(message = "Image url can't be null or empty")
    private String imageUrl;

    @Column(columnDefinition = "boolean default true")
    private boolean status = true;

    @Column(columnDefinition = "boolean default false")
    private boolean special;

    @Embedded
    private Rate rate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private MenuCategory categories;

    public MenuItem() {
    }

}
