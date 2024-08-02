/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cyberbugs.controller;

import com.cyberbugs.model.Comment;
import com.cyberbugs.model.User;
import com.cyberbugs.service.CommentService;
import com.cyberbugs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/comment/post/{postId}")
    public Comment createComment(@RequestBody Comment comment,
            @RequestHeader("Authorization") String jwt,
            @PathVariable Integer postId) throws Exception {

        User user = userService.findUserByJwt(jwt);

        Comment createdComment = commentService.createComment(comment, postId, user.getUid());

        return createdComment;
    }

    @PutMapping("/api/comment/like/{commentId}")
    public Comment likeComment(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Integer commentId) throws Exception {

        User user = userService.findUserByJwt(jwt);

        Comment likedComment = commentService.likeComment(commentId, user.getUid());

        return likedComment;
    }

}
