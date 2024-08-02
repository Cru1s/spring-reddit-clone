/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cyberbugs.service;

import com.cyberbugs.model.Post;
import java.util.List;

/**
 *
 * @author Cruis
 */
public interface PostService {
    
    Post createNewPost(Post post, Integer userId) throws Exception;
    
    String deletePost(Integer postId, Integer userId) throws Exception;
    
    List<Post> findPostByUserId(Integer userId);
    
    Post findPostById(Integer postId) throws Exception;
    
    List<Post> findAllPost();
    
    Post savePost(Integer postId, Integer userId) throws Exception;
    
    Post likePost(Integer postId, Integer userId) throws Exception;
}
