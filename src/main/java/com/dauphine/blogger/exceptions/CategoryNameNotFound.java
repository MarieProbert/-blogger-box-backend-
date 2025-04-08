package com.dauphine.blogger.exceptions;

public class CategoryNameNotFound extends RuntimeException {
    private final String categoryName;

    public CategoryNameNotFound(String categoryName) {
        super("Category name: " + categoryName + " not found.");
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }
}