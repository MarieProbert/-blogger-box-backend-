package com.dauphine.blogger.services;

import com.dauphine.blogger.models.Post;

import java.util.List;
import java.util.UUID;

public interface PostService {

        List<Post> getAllByCategoryId(UUID categoryID);

        List<Post> getAll();

        Post getById(UUID id);

        Post create(String title, String content, UUID categoryID);

        Post update(UUID id, String title, String content);

        boolean delete(UUID id);

        List<Post> getAllByTitle(String title);

}
