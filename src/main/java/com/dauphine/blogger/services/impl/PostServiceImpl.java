package com.dauphine.blogger.services.impl;

import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.services.PostService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final List<Post> temporaryPosts;

    public PostServiceImpl() {
        temporaryPosts = new ArrayList<>();
        Category category = new Category(UUID.randomUUID(), "Sample Category");

        temporaryPosts.add(new Post(UUID.randomUUID(), "First Post", "Content of first post", new Timestamp(System.currentTimeMillis()), category));
        temporaryPosts.add(new Post(UUID.randomUUID(), "Second Post", "Content of second post", new Timestamp(System.currentTimeMillis()), category));
    }

    @Override
    public List<Post> getAllByCategoryId(UUID categoryID) {
        return temporaryPosts.stream()
                .filter(post -> post.getCategory() != null && categoryID.equals(post.getCategory().getId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Post> getAll() {
        return temporaryPosts;
    }

    @Override
    public Post getById(UUID id) {
        return temporaryPosts.stream()
                .filter(post -> id.equals(post.getId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Post create(String title, String content, UUID categoryID) {
        Category category = new Category(categoryID, "Category Placeholder"); // Placeholder category
        Post post = new Post(UUID.randomUUID(), title, content, new Timestamp(System.currentTimeMillis()), category);
        temporaryPosts.add(post);
        return post;
    }

    @Override
    public Post update(UUID id, String title, String content) {
        Post post = temporaryPosts.stream()
                .filter(p -> id.equals(p.getId()))
                .findFirst()
                .orElse(null);

        if (post != null) {
            post.setTitle(title);
            post.setContent(content);
        }

        return post;
    }

    @Override
    public boolean delete(UUID id) {
        return temporaryPosts.removeIf(post -> id.equals(post.getId()));
    }
}
