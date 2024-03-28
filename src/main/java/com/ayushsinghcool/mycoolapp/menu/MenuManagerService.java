package com.ayushsinghcool.mycoolapp.menu;

import com.ayushsinghcool.mycoolapp.menu.model.MenuCategory;
import com.ayushsinghcool.mycoolapp.menu.model.MenuItem;

import java.util.List;

public interface MenuManagerService {
    List<MenuItem> fetchMenu();
    MenuItem fetchMenuById(int id);

    MenuCategory fetchCategoryById(int id);
    boolean createMenu(List<MenuItem> menuItem);
    void addCategory(MenuCategory menuCategory);
    List<MenuCategory> fetchCategory();
    boolean deleteMenu(int id);
    boolean deleteCategory(int id);
    boolean updateMenu(int id,MenuItem menuItem);
    boolean updateCategory(int id,MenuCategory menuCategory);
}
