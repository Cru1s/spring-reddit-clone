/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cyberbugs.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cruis
 */
@Entity
public class Comment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cid;
    
    private String content;
    
    @ManyToOne
    private User user;
    
    @ManyToMany
    private List<User> liked = new ArrayList<>();
    
    private LocalDateTime createdAt;

    public Comment() {
    }

    public Comment(Integer cid, String content, User user, LocalDateTime createdAt) {
        this.cid = cid;
        this.content = content;
        this.user = user;
        this.createdAt = createdAt;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getLiked() {
        return liked;
    }

    public void setLiked(List<User> liked) {
        this.liked = liked;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    
    
}
