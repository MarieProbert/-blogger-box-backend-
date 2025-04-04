package com.dauphine.blogger.services.impl;

import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.repositories.CategoryRepository;
import com.dauphine.blogger.repositories.PostRepository;
import com.dauphine.blogger.services.PostService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;

    public PostServiceImpl(PostRepository postRepository, CategoryRepository categoryRepository) {
        this.postRepository=postRepository;
        this.categoryRepository=categoryRepository;
    }

    @Override
    public List<Post> getAllByCategoryId(UUID categoryID) {
        return postRepository.findAllByCategoryId(categoryID);
    }

    @Override
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    @Override
    public Post getById(UUID id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public Post create(String title, String content, UUID categoryID) {
        Category category = categoryRepository.getById(categoryID);
        Post post = new Post(UUID.randomUUID() ,title, content, new Timestamp(System.currentTimeMillis()),category);
        return postRepository.save(post);
    }

    @Override
    public Post update(UUID id, String title, String content) {
        Post post = getById(id);
        post.setTitle(title);
        post.setContent(content);
        return postRepository.save(post);
    }

    @Override
    public boolean delete(UUID id) {
        postRepository.deleteById(id);
        return true;
    }

}