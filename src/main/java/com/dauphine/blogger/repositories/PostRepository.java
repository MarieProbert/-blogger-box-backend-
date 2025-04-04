package com.dauphine.blogger.repositories;

import com.dauphine.blogger.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;
import java.util.List;


public interface PostRepository extends JpaRepository<Post, UUID> {

    @Query("""
            SELECT post
            FROM Post post
            WHERE post.category.id = :categoryId
    """)
    List<Post> findAllByCategoryId(@Param("categoryId") UUID categoryId);

    @Query("""
            SELECT post
            FROM Post post
            WHERE post.title = :title
    """)
    List<Post> findAllByTitle(@Param("title") String title);
}