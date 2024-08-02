/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cyberbugs.repository;

import com.cyberbugs.model.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Cruis
 */
public interface PostRepository extends JpaRepository<Post, Integer>{
    
    @Query("select p from Post p where p.user.id = :userId") 
    List<Post> findPostByUserId(Integer userId);
    
}
