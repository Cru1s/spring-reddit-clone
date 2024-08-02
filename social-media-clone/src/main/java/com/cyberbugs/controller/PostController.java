/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cyberbugs.controller;

import com.cyberbugs.model.Post;
import com.cyberbugs.model.User;
import com.cyberbugs.response.ApiResponse;
import com.cyberbugs.service.PostService;
import com.cyberbugs.service.UserService;
import java.util.List;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Cruis
 */
@RestController
public class PostController {

    @Autowired
    PostService postService;
    
    @Autowired
    UserService userService;

    @PostMapping("/api/posts")
    public ResponseEntity<Post> createPost(@RequestHeader("Authorization") String jwt, @RequestBody Post post) throws Exception {

        User requestUser = userService.findUserByJwt(jwt);
        
        Post createdPost = postService.createNewPost(post, requestUser.getUid());

        return new ResponseEntity<>(createdPost, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/posts/delete/{postId}")
    public ResponseEntity<String> deletePost(@RequestHeader("Authorization") String jwt, @PathVariable Integer postId) throws Exception {
        
        User requestUser = userService.findUserByJwt(jwt);
        
        String message = postService.deletePost(postId, requestUser.getUid());
//        ApiResponse res = new ApiResponse(message, true);

//        return new ResponseEntity<>(res, HttpStatus.OK);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/api/posts/{postId}")
    public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postId) throws Exception {

        Post post = postService.findPostById(postId);

        return new ResponseEntity<>(post, HttpStatus.ACCEPTED);
    }

    @GetMapping("/posts/user/{userId}")
    public ResponseEntity<List<Post>> findUserPost(@PathVariable Integer userId) {

        List<Post> posts = postService.findPostByUserId(userId);

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/api/posts")
    public ResponseEntity<List<Post>> findAllPost() {

        List<Post> posts = postService.findAllPost();

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PutMapping("/posts/save/{postId}")
    public ResponseEntity<Post> savePostdHandler(@PathVariable Integer postId, @RequestHeader("Authorization") String jwt) throws Exception {
        
        User requestUser = userService.findUserByJwt(jwt);
        
        Post post = postService.savePost(postId, requestUser.getUid());

        return new ResponseEntity<>(post, HttpStatus.ACCEPTED);
    }

    @PutMapping("/posts/like/{postId}")
    public ResponseEntity<Post> likePostdHandler(@PathVariable Integer postId, @RequestHeader("Authorization") String jwt) throws Exception {
        
        User requestUser = userService.findUserByJwt(jwt);

        Post post = postService.likePost(postId, requestUser.getUid());

        return new ResponseEntity<>(post, HttpStatus.ACCEPTED);
    }
}
