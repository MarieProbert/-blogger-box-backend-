package com.dauphine.blogger.exceptions;

public class CategoryNameAlreadyExists extends RuntimeException {
    private final String categoryName;

    public CategoryNameAlreadyExists(String categoryName) {
        super("Category name: " + categoryName + " already exists.");
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }
}