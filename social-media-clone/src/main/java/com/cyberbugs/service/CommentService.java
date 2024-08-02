/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cyberbugs.service;

import com.cyberbugs.model.Comment;

/**
 *
 * @author Cruis
 */
public interface CommentService {
    
    Comment createComment(Comment comment, Integer postId, Integer userId) throws Exception;
    
    Comment findCommentById(Integer commentId) throws Exception;
    
    Comment likeComment(Integer commentId, Integer userId) throws Exception;
    
    
    
}
