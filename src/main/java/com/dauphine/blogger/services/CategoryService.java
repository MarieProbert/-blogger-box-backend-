package com.dauphine.blogger.services;

import com.dauphine.blogger.models.Category;

import java.util.UUID;
import java.util.List;

public interface CategoryService {

    List<Category> getAll();

    List<Category> getByCategoryName(String name);

    Category getById(UUID id);

    Category create(String name);

    Category update(UUID id, String name);

    boolean deleteById(UUID id);
}
