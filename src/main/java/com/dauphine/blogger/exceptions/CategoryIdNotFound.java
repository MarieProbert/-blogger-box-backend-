package com.dauphine.blogger.exceptions;

import java.util.UUID;

public class CategoryIdNotFound extends RuntimeException {
    private final UUID categoryId;
    public CategoryIdNotFound(UUID categoryId) {
        super("Category ID: " + categoryId.toString() + " not found.");
        this.categoryId = categoryId;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

}
