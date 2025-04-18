package com.dauphine.blogger.repositories;

import com.dauphine.blogger.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    // JPQL (similar to SQL)
    @Query("""
            SELECT cat
            FROM Category cat
            WHERE UPPER(cat.name) LIKE UPPER(CONCAT('%', :name, '%'))
    """)
    List<Category> findAllLikeName(@Param("name") String name);

    @Query("""
        SELECT CASE WHEN COUNT(cat) > 0 THEN TRUE ELSE FALSE END
        FROM Category cat WHERE cat.name = :name
    """)
    boolean doesNameExist(@Param("name") String name);
}
