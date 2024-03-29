package com.ayushsinghcool.mycoolapp.menu.impl;

import com.ayushsinghcool.mycoolapp.menu.model.MenuCategory;
import com.ayushsinghcool.mycoolapp.menu.model.MenuItem;
import com.ayushsinghcool.mycoolapp.menu.MenuManagerService;
import com.ayushsinghcool.mycoolapp.repo.MenuCategoryRepo;
import com.ayushsinghcool.mycoolapp.repo.MenuItemRepo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MenuServiceImpl implements MenuManagerService {
    MenuItemRepo menuItemRepo;
    MenuCategoryRepo menuCategoryRepo;
    public MenuServiceImpl(MenuItemRepo menuItemRepo,MenuCategoryRepo menuCategoryRepo) {
        this.menuItemRepo = menuItemRepo;
        this.menuCategoryRepo = menuCategoryRepo;
    }

    public boolean saveMenuItemWithCategory(MenuItem menuItem,String categoryName) {
        MenuCategory category = menuCategoryRepo.findByCategory(categoryName);
        if(category!=null){
            menuItem.setCategories(category);
            menuItemRepo.save(menuItem);
            return true;
        }
            return false;
    }

    @Override
    public List<MenuItem> fetchMenu() {
        return menuItemRepo.findAll();
    }

    @Override
    public MenuItem fetchMenuById(int id) {
        return menuItemRepo.findById(id).orElse(null);
    }

    @Override
    public MenuCategory fetchCategoryById(int id) {
        return menuCategoryRepo.findById(id).orElse(null);
    }

    @Override
    public boolean createMenu(List<MenuItem> menuItems) {
        boolean allSaved = true;
        for (MenuItem menuItem : menuItems) {
            boolean saved = saveMenuItemWithCategory(menuItem, menuItem.getCategories().getCategory());
            if (!saved) {
                allSaved = false;
            }
        }
        return allSaved;
    }

    @Override
    public void addCategory(MenuCategory menuCategory) {
        menuCategoryRepo.save(menuCategory);
    }

    @Override
    public List<MenuCategory> fetchCategory() {
        return menuCategoryRepo.findAll();
    }

    @Override
    public boolean deleteMenu(int id) {
        Optional<MenuItem> menuItemOptional = menuItemRepo.findById(id);
            if(menuItemOptional.isPresent()){
                menuItemRepo.deleteById(id);
                return true;
            }
            else {
                return false;
            }
    }

    @Override
    public boolean deleteCategory(int id) {
        Optional<MenuCategory> menuCategoryOptional = menuCategoryRepo.findById(id);
        if(menuCategoryOptional.isPresent()){
            menuCategoryRepo.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean updateMenu(int id, MenuItem updatedMenuItem) {
        Optional<MenuItem> menuItemOptional = menuItemRepo.findById(id);
        if(menuItemOptional.isPresent()){
            MenuItem menuItem = menuItemOptional.get();
            menuItem.setName(updatedMenuItem.getName());
            menuItem.setDescription(updatedMenuItem.getDescription());
            menuItem.setRate(updatedMenuItem.getRate());
            String categoryName = updatedMenuItem.getCategories().getCategory();
            MenuCategory category = menuCategoryRepo.findByCategory(categoryName);
            if (category != null) {
                menuItem.setCategories(category);
            } else {
                return false;
            }
            menuItemRepo.save(menuItem);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateCategory(int id, MenuCategory updateMenuCategory) {
        Optional<MenuCategory> menuCategoryOptional = menuCategoryRepo.findById(id);
        if(menuCategoryOptional.isPresent()){
            MenuCategory menuCategory =menuCategoryOptional.get();
            menuCategory.setCategory(updateMenuCategory.getCategory());
            menuCategory.setId(updateMenuCategory.getId());
            menuCategoryRepo.save(menuCategory);
            return true;
        }
        else {
            return false;
        }
    }
}
