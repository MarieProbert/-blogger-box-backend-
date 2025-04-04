package com.dauphine.blogger.models;

import java.util.UUID;
import java.sql.Timestamp;

public class Post {

    private UUID id;
    private String title;
    private String content;
    private Timestamp createdDate;
    private Category category;

    public Post() {}

    public Post(UUID id, String title) {
        this.id = id;
        this.title = title;
    }

    public Post(UUID id, String title, String content, Timestamp createdDate, Category category) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.category = category;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }

}
