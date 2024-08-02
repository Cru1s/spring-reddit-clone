/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cyberbugs.repository;

import com.cyberbugs.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Cruis
 */
public interface CommentRepository extends JpaRepository<Comment, Integer>{
    
}
