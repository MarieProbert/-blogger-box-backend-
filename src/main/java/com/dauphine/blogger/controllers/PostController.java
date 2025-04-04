package com.dauphine.blogger.controllers;

import com.dauphine.blogger.dto.PostRequest;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/v1/posts")
public class PostController {

    private final List<Post> temporaryPosts;

    private final CategoryController categoryController;

    @Autowired
    public PostController(CategoryController categoryController) {
        temporaryPosts = new ArrayList<>();
        temporaryPosts.add(new Post(UUID.randomUUID(), "my first post"));
        temporaryPosts.add(new Post(UUID.randomUUID(), "my second post"));
        temporaryPosts.add(new Post(UUID.randomUUID(), "my third post"));
        this.categoryController = categoryController;
    }

    @GetMapping
    public List<Post> Posts() {
        return temporaryPosts;
    }

    @PostMapping()
    public Post create(@RequestBody PostRequest postRequest) {
        Post post = new Post();
        post.setId(UUID.randomUUID());
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        post.setCreatedDate(postRequest.getCreatedDate());

        // Utilisation de la méthode retrieveCategoryById de CategoryController
        post.setCategory(categoryController.retrieveCategoryById(postRequest.getCategoryId()));

        temporaryPosts.add(post);
        return post;
    }

}
