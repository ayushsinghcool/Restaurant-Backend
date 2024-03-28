package com.ayushsinghcool.mycoolapp.repo;

import com.ayushsinghcool.mycoolapp.menu.model.MenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuCategoryRepo extends JpaRepository<MenuCategory,Integer> {
    MenuCategory findByCategory(String category);
}
