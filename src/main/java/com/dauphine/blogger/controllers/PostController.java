package com.dauphine.blogger.controllers;

import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.services.CategoryService;
import com.dauphine.blogger.services.PostService;
import com.dauphine.blogger.services.impl.PostServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/posts")
public class PostController {
    private final PostServiceImpl postService;

    public PostController(PostServiceImpl postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getPosts(@RequestParam(required = false) String title) {
        List<Post> posts = title == null || title.isBlank()
                ? postService.getAll()
                : postService.getAllByTitle(title);
        return posts;
    }

    @GetMapping("/{id}")
    public Post getPost(@PathVariable UUID id) {
        return postService.getById(id);
    }

    @PostMapping
    public void createPost(@RequestBody Post post) {
        postService.create(post.getTitle(),post.getContent(), post.getCategory().getId());
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable UUID id) {
        postService.delete(id);
    }

    @PutMapping("/{id}")
    public void updatePost(@PathVariable UUID id, @RequestBody Post post) {
        postService.update(id,post.getTitle(),post.getContent());
    }
}