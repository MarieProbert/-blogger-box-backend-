package com.dauphine.blogger.exceptions;

import java.util.UUID;

public class PostIdNotFound extends RuntimeException {
    private final UUID postId;
    public PostIdNotFound(UUID postId) {
        super("Post ID: " + postId.toString() + " not found.");
        this.postId = postId;
    }

    public UUID getCategoryId() {
        return postId;
    }
}