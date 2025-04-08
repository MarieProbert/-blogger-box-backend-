package com.dauphine.blogger.controllers;

import com.dauphine.blogger.dto.PostRequest;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.services.CategoryService;
import com.dauphine.blogger.services.PostService;
import com.dauphine.blogger.services.impl.PostServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/posts")
public class PostController {
    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping
    public List<Post> getPosts(@RequestParam(required = false) String title) {
        List<Post> posts = title == null || title.isBlank()
                ? service.getAll()
                : service.getAllByTitle(title);
        return ResponseEntity.ok(posts).getBody();
    }

    @GetMapping("/{id}")
    public Post getPost(@PathVariable UUID id) {
        Post post = service.getById(id);
        return ResponseEntity.ok(post).getBody();
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Post>> getAllPostsByCategoryId(@PathVariable UUID categoryId) {
        return ResponseEntity.ok(this.service.getAllByCategoryId(categoryId));
    }


    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody PostRequest postRequest) {
        Post created = this.service.create(postRequest.getTitle(), postRequest.getContent(), postRequest.getCategoryId());
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable UUID id, @RequestBody PostRequest postRequest) {
        Post updated = this.service.update(id, postRequest.getTitle(), postRequest.getContent());
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePostById(@PathVariable UUID id) {
        boolean deleted = this.service.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build();  // 404 Not Found
        }
    }

}