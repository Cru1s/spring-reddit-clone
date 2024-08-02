/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cyberbugs.service;

import com.cyberbugs.model.Post;
import com.cyberbugs.model.User;
import com.cyberbugs.repository.PostRepository;
import com.cyberbugs.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Cruis
 */
@Service
public class PostServiceImplementation implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Override
    public Post createNewPost(Post post, Integer userId) throws Exception {

        User user = userService.findUserById(userId);

        Post newPost = new Post();
        newPost.setCaption(post.getCaption());
        newPost.setImage(post.getImage());
        newPost.setCreateAt(LocalDateTime.now());
        newPost.setVideo(post.getVideo());
        newPost.setUser(user);

        return postRepository.save(newPost);
    }

    @Override
    public String deletePost(Integer postId, Integer userId) throws Exception {

        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        if (!Objects.equals(post.getUser().getUid(), user.getUid())) {
            throw new Exception("Unable to delete post from another user");
        }
        
        userRepository.removePostFromSavedPosts(post);
        
        postRepository.delete(post);

        return "post delete successfully";
    }

    @Override
    public List<Post> findPostByUserId(Integer userId) {

        return postRepository.findPostByUserId(userId);

    }

    @Override
    public Post findPostById(Integer postId) throws Exception {

        Optional<Post> post = postRepository.findById(postId);

        if (post.isEmpty()) {
            throw new Exception("post not found");
        }

        return post.get();

    }

    @Override
    public List<Post> findAllPost() {

        return postRepository.findAll();
    }

    @Override
    public Post savePost(Integer postId, Integer userId) throws Exception {

        Post post = findPostById(postId);
        User user = userService.findUserById(userId);
        
        if(user.getSavedPost().contains(post)){
            user.getSavedPost().remove(post);
        }
        else {
            user.getSavedPost().add(post);
        }
        
        userRepository.save(user);
        
        return post;
        
    }

    @Override
    public Post likePost(Integer postId, Integer userId) throws Exception {

        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        if (post.getLiked().contains(user)) {
            post.getLiked().remove(user);
        } else {
            post.getLiked().add(user);
        }

        return postRepository.save(post);

    }

}
