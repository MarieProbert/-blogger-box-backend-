package com.dauphine.blogger.services.impl;

import com.dauphine.blogger.exceptions.CategoryIdNotFound;
import com.dauphine.blogger.exceptions.CategoryNameAlreadyExists;
import com.dauphine.blogger.exceptions.CategoryNameNotFound;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.repositories.CategoryRepository;
import com.dauphine.blogger.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Category> getAll() {
        return repository.findAll();
    }

    @Override
    public Category getById(UUID id) throws CategoryIdNotFound {
        return repository.findById(id)
                .orElseThrow(() -> new CategoryIdNotFound(id));
    }

    @Override
    public Category create(String name) throws CategoryNameAlreadyExists {
        Category newCategory = new Category();
        newCategory.setName(name);
        newCategory.setId(UUID.randomUUID());

        if (repository.doesNameExist(name)){
            throw new CategoryNameAlreadyExists(name);
        } else {
            return repository.save(newCategory);
        }

    }

    @Override
    public Category update(UUID id, String newName) throws CategoryIdNotFound {
        Category category = getById(id);
        if (category == null){
            return null;
        }
        category.setName(newName);
        return repository.save(category);
    }

    @Override
    public boolean deleteById(UUID id) throws CategoryIdNotFound{
        repository.deleteById(id);
        return true;
    }

    @Override
    public List<Category> getByCategoryName(String name) throws CategoryNameNotFound {
        return repository.findAllLikeName(name);
    }

}
