package com.ayushsinghcool.mycoolapp.repo;

import com.ayushsinghcool.mycoolapp.menu.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepo extends JpaRepository<MenuItem,Integer> {
}
